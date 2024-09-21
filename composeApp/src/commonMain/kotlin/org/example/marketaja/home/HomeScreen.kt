package org.example.marketaja.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.example.marketaja.home.section_content.HomeContentCategoriesComponent
import org.example.marketaja.home.section_head.HomeSearchComponent
import org.example.marketaja.home.section_head.HomeToolbarComponent

@Composable
fun HomeScreen() {
    val cols = remember { 2 }
    val itemSpan: (LazyGridItemSpanScope) -> GridItemSpan = remember(cols) {
        { GridItemSpan(cols) }
    }

    Scaffold(
        topBar = {
            HomeToolbarComponent()
        }
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    color = Color(0xFFf5f5f7)
                )
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize(),
                columns = GridCells.Fixed(2)
            ) {
                item(span = itemSpan) {
                    HomeSearchComponent()
                }

                item(span = itemSpan) {
                    Spacer(modifier = Modifier.height(10.dp))
                }

                item(span = itemSpan) {
                    HomeContentCategoriesComponent()
                }
            }
        }
    }
}