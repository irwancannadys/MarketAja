package org.example.marketaja.home.section_content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.navigation.LocalAppNavigator
import org.example.marketaja.market_component.MarketButtonComponent


@Composable
fun HomeContentCategoriesComponent() {

    val navigationService = LocalAppNavigator.current

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


        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(6) {
                CategoriesItem()
            }
        }

        val exampleValue = "Mechanical Keyboard"
        Spacer(modifier= Modifier.height(16.dp))
        MarketButtonComponent(
            buttonText = "Add To Cart",
            onClickButton = {
                navigationService.navigateToProductDetail(exampleValue)
            }
        )
    }
}

@Composable
fun CategoriesItem() {
    Column {
        Box(
            modifier = Modifier.background(
                color = Color(0xFFebf5f4),
                shape = CircleShape
            )
        ) {
            Icon(
                modifier = Modifier.padding(
                    10.dp
                ),
                imageVector = Icons.Rounded.ShoppingCart,
                contentDescription = null
            )
        }
        Spacer(modifier= Modifier.height(8.dp))
        Text("Komputer")
    }
}