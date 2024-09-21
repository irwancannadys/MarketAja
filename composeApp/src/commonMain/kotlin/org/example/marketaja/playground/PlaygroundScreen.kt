package org.example.marketaja.playground

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun PlaygroundScreen() {

    val listName = listOf(
        "Irwan",
        "Joko",
        "Budi",
        "Mulyono"
    )

    Scaffold(
        topBar = {
            TopBarMarketAja()
        }
    ) {
        Column {
            ContainerColumn()
            Spacer(modifier = Modifier.height(16.dp))
            ContainerRow()
            ContainerList(data = listName)
        }
    }
}

@Composable
fun ContainerColumn() {
    Column(
        modifier = Modifier.background(
            color = Color.Cyan

        ).fillMaxWidth()
    ) {
        Text("Nama")
        Text("Alamat")
        Button(onClick = {}) {
            Text("Go to next page")
        }
    }
}

@Composable
fun ContainerRow() {
    Row(
        modifier = Modifier.background(
            color = Color.LightGray
        ).fillMaxWidth()
    ) {
        Text("Nama")
        Text("Alamat")
        Button(onClick = {}) {
            Text("Go to next page")
        }
    }
}

@Composable
fun ContainerList(data: List<String>) {

    val cols = remember { 2 }
    val itemSpan: (LazyGridItemSpanScope) -> GridItemSpan = remember(cols) {
        { GridItemSpan(cols) }
    }

//    LazyColumn {
//        item {
//            Text(
//                text = "nama1",
//                fontSize = 22.sp
//            )
//        }
//        item {
//            Text(
//                text = "nama2",
//                fontSize = 22.sp
//            )
//        }
//        items(data.size) {
//            Text(
//                text = it.toString(),
//                fontSize = 22.sp
//            )
//        }
//    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        item(
            span = itemSpan
        ) {
            Column(
                modifier = Modifier.background(Color.Blue)
            ) {
                data.forEach { stringName ->
                    Text(
                        text = stringName,
                        fontSize = 22.sp
                    )
                }
            }
        }

        item {
            Column(
                modifier = Modifier.background(Color.Red)
            ) {
                data.forEach { stringName ->
                    Text(
                        text = stringName,
                        fontSize = 22.sp
                    )
                }
            }
        }
    }
}

@Composable
fun TopBarMarketAja(modifier: Modifier = Modifier) {

}