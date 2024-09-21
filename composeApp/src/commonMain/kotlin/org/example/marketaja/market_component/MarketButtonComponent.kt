package org.example.marketaja.market_component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MarketButtonComponent(
    buttonText: String = "Button",
    onClickButton: () -> Unit = {}
) {
    Button(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 16.dp).height(45.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
        onClick = {
            onClickButton.invoke()
        }
    ) {
        Text(
            text = buttonText
        )
    }
}