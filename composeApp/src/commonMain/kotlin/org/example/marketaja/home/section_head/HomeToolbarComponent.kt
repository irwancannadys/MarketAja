package org.example.marketaja.home.section_head

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.core.navigation.LocalAppNavigator


@Composable
fun HomeToolbarComponent() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HomeDiscountComponent() // coordinate dimulai start
        HomeAddressUserComponent() // coordinate dimulai base content position
        HomeNotificationComponent() // coordinate dimulai end
    }
}

@Composable
private fun HomeDiscountComponent() {
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
}

@Composable
fun HomeAddressUserComponent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Delivery Address", style = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal
        ))
        Text("Jl Pamulang Permai", style = TextStyle(
            fontSize = 16.sp,
        ))
    }
}

@Composable
fun HomeNotificationComponent() {
    val appNavigator = LocalAppNavigator.current
    Box(
        modifier = Modifier.background(
            color = Color.White,
            shape = CircleShape
        ).clickable {
            appNavigator.navigateToFavorite()
        }
    ) {
        Icon(
            modifier = Modifier.padding(
                10.dp
            ),
            imageVector = Icons.Rounded.Notifications,
            contentDescription = null
        )
    }
}