package org.example.marketaja.home.section_content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForward
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

        Text("dasdsa")
        Text("dasdsa")

        MarketButtonComponent(
            buttonText = "Add To Cart",
            onClickButton = {

            }
        )
    }
}