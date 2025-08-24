package com.example.myapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ModosPage(navController: NavController) {
    // Lista de mapas de ejemplo
    val mapas = listOf("Bosque", "Desierto", "Ciudad", "Montaña", "Playa", "Frutas")
    var mapaSeleccionado by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Atrás"
                    )
                }
                Text(
                    text = "Mapas",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Grid 2x3
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    for (row in 0 until 2) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            for (col in 0 until 3) {
                                val index = row * 3 + col
                                if (index < mapas.size) {
                                    val nombreMapa = mapas[index]
                                    Box(
                                        modifier = Modifier
                                            .size(100.dp)
                                            .background(
                                                color = Color.LightGray,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .border(
                                                width = 3.dp,
                                                color = if (mapaSeleccionado == nombreMapa) Color.Blue else Color.Transparent,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .clickable { mapaSeleccionado = nombreMapa },
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(nombreMapa)
                                    }
                                }
                            }
                        }
                    }
                }

                // Sección inferior con descripción
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color(0xFFEFEFEF), shape = RoundedCornerShape(12.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (mapaSeleccionado != null) {
                        Text(
                            text = "Descripción del mapa seleccionado: $mapaSeleccionado",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    } else {
                        Text(
                            text = "Selecciona un mapa para ver su descripción",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    )
}
