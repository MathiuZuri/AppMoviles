package com.example.myapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CharactersPage(navController: NavController) {
    val personajes = List(16) { "Personaje ${it + 1}" }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Atrás")
                }
                Text("Personajes", style = MaterialTheme.typography.titleLarge)
            }
        },
        content = { innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                itemsIndexed(personajes) { index, personaje ->
                    Box(
                        modifier = Modifier
                            .aspectRatio(1f) // cuadrado
                            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .clickable { navController.navigate("personajeDetalle/$index") },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(personaje)
                    }
                }
            }
        }
    )
}
