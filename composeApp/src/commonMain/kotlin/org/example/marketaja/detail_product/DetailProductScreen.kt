package org.example.marketaja.detail_product

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.example.core.service.onFailure
import com.example.core.service.onLoading
import com.example.core.service.onSuccess
import com.example.data.response.DetailProductResponse
import org.example.marketaja.di.viewModel
import org.example.marketaja.market_component.MarketButtonComponent


@Composable
fun DetailProductScreen(id: Int) {

    val viewModel by viewModel<DetailProductViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.sendAction(DetailProductAction.setDetailProductId(id))
        viewModel.sendAction(DetailProductAction.GetDetailProduct)
    }

    with(state.detailResponseAsync) {
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
            LoadContentDetail(data)
        }
        onFailure {  }
    }
}

@Composable
fun LoadContentDetail(
    data: DetailProductResponse.Data?
) {
    Scaffold(
        topBar = { ToolBarDetailProduct(data?.name ?: "") }
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(color = Color.Gray)
        ) {
            item {
                ImageSlider(
                    data?.images ?: listOf()
                )
            }

            item {
                ContentDetailProduct(data)
            }
        }
    }
}

@Composable
fun ContentDetailProduct(
    data: DetailProductResponse.Data?
) {
    Column(
        modifier = Modifier.fillMaxHeight().fillMaxWidth().background(
            color = Color.White,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp
            )
        )
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = data?.name ?: "",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        )

        MarketButtonComponent(
            buttonText = "Add to Cart"
        ) {  }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(
    listImage: List<String>
) {

    val pagerState = rememberPagerState(
        pageCount = { listImage.size }
    )

    HorizontalPager(
        state = pagerState,
    ) { page ->
        ImageSliderItem(listImage[page])
    }
}

@Composable
fun ImageSliderItem(
    url: String
) {
    SubcomposeAsyncImage(
        modifier = Modifier.fillMaxWidth().height(180.dp),
        model = url,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun ToolBarDetailProduct(name: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
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

}