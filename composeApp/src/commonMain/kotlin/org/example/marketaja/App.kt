package org.example.marketaja

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.example.core.local.ValueStoreManager
import com.example.core.navigation.LocalAppNavigator
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuitx.gesturenavigation.GestureNavigationDecoration
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import okio.FileSystem
import org.example.marketaja.detail_product.DetailProductScreen
import org.example.marketaja.favorite.FavoriteScreen
import org.example.marketaja.home.HomeScreen
import org.example.marketaja.login.LoginScreen
import org.example.marketaja.navigation.AppScreen
import org.example.marketaja.navigation.AppScreenImpl
import org.example.marketaja.product.ProductScreen

@OptIn(ExperimentalCoilApi::class)
@Composable
fun App(
    backPressConsumer: Channel<Unit> = Channel()
) {
    MaterialTheme {

        setSingletonImageLoaderFactory { context ->
            getAsyncImageLoader(context)
        }

        val backstack = rememberSaveableBackStack(
            root = AppScreen.Login
        )

        val navigator = rememberCircuitNavigator(backStack = backstack, onRootPop = {
            exitApp()
        })

        val appNavigator = remember { AppScreenImpl(navigator) }

        LaunchedEffect(Unit) {
            backPressConsumer.consumeEach {
                navigator.pop()
            }
        }

        val valueManager = ValueStoreManager()

        val circuit = remember {
            Circuit.Builder()
                .setOnUnavailableContent { screen, modifier ->
                    CompositionLocalProvider(
                        LocalAppNavigator provides appNavigator
                    ) {
                        when (screen) {
                            is AppScreen.Login -> {
                                if (valueManager.token.isNotEmpty()) {
                                    appNavigator.navigateToHome()
                                } else {
                                    LoginScreen()
                                }
                            }

                            is AppScreen.Home -> HomeScreen()
                            is AppScreen.ProductList -> ProductScreen(
                                screen.id, screen.name
                            )

                            is AppScreen.Favorite -> FavoriteScreen()


                            is AppScreen.ProductDetail -> DetailProductScreen(
                                screen.id
                            )

                            else -> Text("Route: ${screen::class.simpleName} not found!")
                        }
                    }
                }
                .build()
        }

        CircuitCompositionLocals(circuit) {
            NavigableCircuitContent(
                navigator = navigator,
                backStack = backstack,
                decoration = GestureNavigationDecoration {
                    backstack.pop()
                }
            )
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
    }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
        newDiskCache()
    }.crossfade(true).logger(DebugLogger()).build()

fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "image_cache")
        .maxSizeBytes(1024L * 1024 * 1024) // 512MB
        .build()
}