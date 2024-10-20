package org.example.marketaja.home.section_content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.navigation.LocalAppNavigator
import com.example.core.service.onFailure
import com.example.core.service.onLoading
import com.example.core.service.onSuccess
import com.example.data.response.CategoryResponse
import org.example.marketaja.di.viewModel
import org.example.marketaja.home.HomeAction
import org.example.marketaja.home.HomeViewModel


@Composable
fun HomeContentCategoriesComponent() {
    val viewModel by viewModel<HomeViewModel>()
    val state by viewModel.state.collectAsState()
    val navigationService = LocalAppNavigator.current

    LaunchedEffect(Unit) {
        viewModel.sendAction(HomeAction.GetCategory)
    }

    with(state.categoryResponseAsync) {
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
            LoadCategoryContent(
                data = data,
                onClick = { id, name ->
                    navigationService.navigateToProductList(
                        id, name
                    )
                }
            )
        }

        onFailure { it ->
            // showing toast
            println("failed nih ${it.message}")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoadCategoryContent(
    data: List<CategoryResponse.Data>,
    onClick: (Int, String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().background(
            color = Color.White,
            shape = RoundedCornerShape(topEnd = 14.dp, topStart = 14.dp)
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Categories",
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight(600)
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "See all", style = TextStyle(
                        fontSize = 14.sp
                    )
                )
                Icon(
                    modifier = Modifier.padding(
                        6.dp
                    ),
                    imageVector = Icons.Rounded.ArrowForward,
                    contentDescription = null,
                    tint = Color.Gray
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            modifier = Modifier.padding(horizontal = 14.dp),
            maxItemsInEachRow = 4,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            data.forEach { data ->
                CategoriesItem(
                    item = data,
                    onClick = { id, name ->
                        onClick.invoke(id, name)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CategoriesItem(
    item: CategoryResponse.Data,
    onClick: (Int, String) -> Unit
) {
    Column {
        Box(
            modifier = Modifier.background(
                color = Color(0xFFebf5f4),
                shape = CircleShape
            ).clickable {
                onClick.invoke(item.id ?: 0, item.name ?: "")
            }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 10.dp),
                text = item.name ?: ""
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}