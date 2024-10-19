package org.example.marketaja.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import com.example.core.navigation.LocalAppNavigator
import com.example.core.service.onFailure
import com.example.core.service.onLoading
import com.example.core.service.onSuccess
import com.example.core.utils.toRupiah
import com.example.data.response.ProductListResponse
import org.example.marketaja.di.viewModel
import org.example.marketaja.market_component.MarketButtonComponent


@Composable
fun ProductScreen(
    idProduct: Int,
    name: String
) {

    val navigation = LocalAppNavigator.current

    val viewModel by viewModel<ProductViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendAction(ProductAction.SetCategoryId(idProduct))
        viewModel.sendAction(ProductAction.GetProductList)
    }

    with(state.productResponseAsync) {
        onLoading {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(48.dp)
                )
            }
        }

        onSuccess { data ->
            if (data.isNullOrEmpty()) {
                // function composable
            } else {
                LoadProductListContent(
                    name = name,
                    data = data,
                    viewModel = viewModel,
                    state = state,
                    clickFavorite = {
                        navigation.navigateToFavorite()
                    }
                )
            }
        }

        onFailure {
            // showing toast
            println("failed nih ${it.message}")
        }
    }
}

@Composable
fun LoadProductListContent(
    name: String,
    data: List<ProductListResponse.Data>?,
    viewModel: ProductViewModel,
    state: ProductState,
    clickFavorite: () -> Unit
) {
    val scrollState = rememberLazyGridState()
    Scaffold(
        topBar = {
            ToolbarProductList(
                name = name,
                clickFavorite = {
                    clickFavorite.invoke()
                }
            )
        }
    ) {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize(),
            columns = GridCells.Fixed(2),
            state = scrollState,
            contentPadding = PaddingValues(16.dp)
        ) {
            data?.size?.let { index ->
                items(index) {
                    ProductListItem(
                        item = data[it],
                        viewModel = viewModel,
                        state = state
                    )
                }
            }
        }
    }
}

@Composable
fun ProductListItem(
    item: ProductListResponse.Data,
    viewModel: ProductViewModel,
    state: ProductState
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier.padding(8.dp).fillMaxWidth(),
        elevation = 2.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalArrangement = Arrangement.Center,
        ) {
//            AsyncImage(
//                modifier = Modifier.height(130.dp).padding(8.dp)
//                    .align(Alignment.CenterHorizontally),
//                model = "https://raw.githubusercontent.com/utsmannn/utsmannn/master/images/Glassware%20Set/img-1.jpeg",
//                contentDescription = null,
//            )

            SubcomposeAsyncImage(
                modifier = Modifier.height(130.dp).padding(8.dp)
                    .align(Alignment.CenterHorizontally),
                model = item.images,
                contentDescription = null,
                loading = {
                    CircularProgressIndicator()
                },

            )

            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.name,
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp)
                    .heightIn(min = 40.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.price.toRupiah(),
                textAlign = TextAlign.Start,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.wrapContentWidth()
                    .padding(horizontal = 8.dp).heightIn(min = 40.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            MarketButtonComponent(
                buttonText = "Beli"
            ) {
            }
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 16.dp).heightIn(min = 40.dp),
                onClick = {
                    if (item.isFavorite) {
//                        viewModel.sendAction(ProductAction.SetFavoriteTextButton(false))
                        viewModel.sendAction(ProductAction.RemoveFavorite(item.id))
                    } else {
//                        viewModel.sendAction(ProductAction.SetFavoriteTextButton(true))
                        viewModel.sendAction(ProductAction.SetFavorite(item))
                    }
                }
            ) {
                if (item.isFavorite) {
                    Text(
                        "Remove Favorite", style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                } else {
                    Text(
                        "Add Favorite", style = TextStyle(
                            fontSize = 12.sp
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun ToolbarProductList(
    name: String,
    clickFavorite: () -> Unit
) {
    val appNavigator = LocalAppNavigator.current
    Box(
        modifier = Modifier.fillMaxWidth().background(
            color = Color.White,
            shape = CircleShape
        ).clickable {
            appNavigator.pop()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    modifier = Modifier.padding(
                        10.dp
                    ).size(48.dp),
                    imageVector = Icons.Rounded.KeyboardArrowLeft,
                    contentDescription = null
                )

                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = name,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            Icon(
                modifier = Modifier.padding(
                    10.dp
                ).size(32.dp)
                    .clickable {
                        clickFavorite.invoke()
                    },
                imageVector = Icons.Rounded.Favorite,
                contentDescription = null
            )

        }
    }
}