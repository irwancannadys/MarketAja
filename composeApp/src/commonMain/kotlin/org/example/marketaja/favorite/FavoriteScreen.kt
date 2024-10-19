package org.example.marketaja.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.example.core.utils.toRupiah
import com.example.data.response.ProductListResponse
import org.example.marketaja.di.viewModel
import org.example.marketaja.market_component.MarketButtonComponent
import org.example.marketaja.product.ProductAction


@Composable
fun FavoriteScreen() {

    val viewModel by viewModel<FavoriteViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendAction(FavoriteAction.GetFavorite)
    }

    LoadFavoriteContent(state.listOfFavorite)
}

@Composable
fun LoadFavoriteContent(
    listFavorite : List<ProductListResponse.Data>
) {
    Scaffold {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Column {
                    Text("Favorite Screen")
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            itemsIndexed(listFavorite) { index, data ->
                FavoriteItem(data)
            }
        }
    }
}

@Composable
fun FavoriteItem(
    item : ProductListResponse.Data
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
        }
    }
}