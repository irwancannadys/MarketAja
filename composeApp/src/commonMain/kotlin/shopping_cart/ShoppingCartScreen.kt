package shopping_cart

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.marketaja.di.viewModel


@Composable
fun ShoppingCartScreen() {

    val viewModel by viewModel<ShoppingCartViewModel>()

    val shoppingCartResponse by viewModel.shoppingCartData.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()


    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text("Shopping Cart")

            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(shoppingCartResponse.size) { position ->
                    val data = shoppingCartResponse[position]
                    ItemCart(
                        item = data,
                        onDecrease = {
                            viewModel.sendAction(ShoppingCartAction.DecreaseQuantity(data.id))
                        },
                        onIncrease = {
                            viewModel.sendAction(ShoppingCartAction.IncreaseQuantity(data.id))
                        },
                        onCheckedChange = { isChecked ->
                            viewModel.sendAction(ShoppingCartAction.ToggleChecked(data.id, isChecked))
                        }
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier.align(alignment = Alignment.CenterVertically),
                    text = "Total: Rp$totalPrice"
                )
                Button(
                    onClick = {}
                ) {
                    Text("Checkout", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun ItemCart(
    item: CartResponse,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Checkbox(
            checked = item.check,
            onCheckedChange = onCheckedChange
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(text = item.name)
            Text(text = "Rp ${item.price}")
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.clickable {
                    onDecrease.invoke()
                },
                text = "-"
            )
            Text(
                modifier = Modifier.padding(horizontal = 12.dp),
                text = "${item.quantity}"
            )
            Text(
                modifier = Modifier.clickable {
                    onIncrease.invoke()
                },
                text = "+"
            )
        }
    }
}