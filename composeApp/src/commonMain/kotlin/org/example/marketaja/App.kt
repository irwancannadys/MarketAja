package org.example.marketaja

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.slack.circuitx.gesturenavigation.GestureNavigationDecoration
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import org.example.marketaja.detail_product.DetailProductScreen
import org.example.marketaja.favorite.FavoriteScreen
import org.example.marketaja.home.HomeScreen
import org.example.marketaja.navigation.AppScreen
import org.example.marketaja.navigation.AppScreenImpl
import org.example.marketaja.navigation.LocalAppNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    backPressConsumer: Channel<Unit> = Channel()
) {
    MaterialTheme {
        val backstack = rememberSaveableBackStack(
            root = AppScreen.Home
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

        val circuit = remember {
            Circuit.Builder()
                .setOnUnavailableContent { screen, modifier ->
                    CompositionLocalProvider(
                        LocalAppNavigator provides appNavigator
                    ) {
                        when (screen) {
                            is AppScreen.Home -> HomeScreen()
                            is AppScreen.Favorite -> FavoriteScreen()
                            is AppScreen.ProductDetail -> DetailProductScreen(screen.nameProduct)
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