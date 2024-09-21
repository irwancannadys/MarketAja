package org.example.marketaja.home.section_content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.marketaja.market_component.MarketButtonComponent


@Composable
fun HomeContentCategoriesComponent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Categories",
                style = TextStyle(
                    fontSize = 23.sp,
                    fontWeight = FontWeight(400)
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "See all", style = TextStyle(
                        fontSize = 11.sp
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

        MarketButtonComponent(
            buttonText = "Add To Cart",
            onClickButton = {

            }
        )
    }
}