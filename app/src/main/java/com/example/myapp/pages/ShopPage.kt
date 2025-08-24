package com.example.myapp.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ShopPage(navController: NavController) {
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    // Items = 4 layouts diferentes (carrusel)
    val layouts = listOf("layout1", "layout2", "layout3", "layout4")

    // Auto-scroll cada 3 segundos
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            val nextIndex = (listState.firstVisibleItemIndex + 1) % layouts.size
            scope.launch {
                listState.animateScrollToItem(nextIndex)
            }
        }
    }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Atrás", color = Color.White)
                }
            }
        },
        content = { innerPadding ->
            LazyRow(
                state = listState,
                flingBehavior = rememberSnapFlingBehavior(listState),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                itemsIndexed(layouts) { index, layout ->
                    Box(
                        modifier = Modifier
                            .fillParentMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        when (layout) {
                            "layout1" -> LayoutTresColumnas()
                            "layout2" -> LayoutGrid2x3()
                            "layout3" -> LayoutColumnasDelgadas()
                            "layout4" -> LayoutGrid2x3()
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun LayoutTresColumnas() {
    Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
        repeat(3) {
            Box(
                Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Text("Columna ${it + 1}", color = Color.White)
            }
        }
    }
}

@Composable
fun LayoutGrid2x3() {
    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {
        repeat(2) { row ->
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(3) { col ->
                    Box(
                        Modifier
                            .size(100.dp)
                            .background(Color.DarkGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Item ${row * 3 + col + 1}", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun LayoutColumnasDelgadas() {
    Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
        repeat(3) {
            Box(
                Modifier
                    .width(60.dp)
                    .fillMaxHeight()
                    .padding(4.dp)
                    .background(Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text("C${it + 1}")
            }
        }
    }
}
