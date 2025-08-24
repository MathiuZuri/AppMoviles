package com.example.myapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CharacterDetailPage(id: String, navController: NavController) {
    val personajeNombre = "Personaje ${id.toInt() + 1}"
    val historia = "Historia o descripción del $personajeNombre. Aquí iría un texto más largo."

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
                Text("Detalles de $personajeNombre", style = MaterialTheme.typography.titleLarge)
            }
        },
        content = { innerPadding ->
            Row(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Columna izquierda (nombre + historia)
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .background(Color(0xFFE0E0E0), RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(personajeNombre, style = MaterialTheme.typography.titleMedium)
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Text(historia, style = MaterialTheme.typography.bodyMedium)
                    }
                }

                // Centro (carrusel simplificado con imagen placeholder)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .fillMaxHeight()
                        .background(Color.LightGray, RoundedCornerShape(8.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Imagen del personaje (carrusel)", color = Color.DarkGray)
                }

                // Derecha (estadísticas)
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StatBox("Vida")
                    StatBox("Daño")
                    StatBox("Poder")
                }
            }
        }
    )
}

@Composable
fun StatBox(label: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color(0xFFD0D0FF), RoundedCornerShape(12.dp))
            .padding(8.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = label,
                tint = Color.Blue
            )
            Spacer(Modifier.width(8.dp))
            Text("$label: ???", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
