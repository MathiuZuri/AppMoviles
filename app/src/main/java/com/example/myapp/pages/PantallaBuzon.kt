package com.example.myapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PantallaBuzon(navController: NavController) {
    var mensajeSeleccionado by remember { mutableStateOf("Selecciona un mensaje") }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding -> // <-- Se recibe el parámetro de padding
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) // <-- Se aplica el padding aquí
                    .padding(16.dp)
            ) {
                // Botón atrás + lista de mensajes
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                    Spacer(Modifier.height(16.dp))

                    LazyColumn {
                        items(10) { index ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                                    .background(Color.Gray, RoundedCornerShape(8.dp))
                                    .clickable { mensajeSeleccionado = "Detalle del mensaje $index" }
                                    .padding(16.dp)
                            ) {
                                Column {
                                    Text("Mensaje $index", style = MaterialTheme.typography.bodyLarge)
                                    Text("Fecha: 2025-08-21", style = MaterialTheme.typography.bodySmall)
                                }
                            }
                        }
                    }

                    Button(
                        onClick = { /* marcar como leído */ },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Marcar como leído")
                    }
                }

                // Detalles mensaje
                Column(
                    modifier = Modifier
                        .weight(2f)
                        .padding(start = 16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(mensajeSeleccionado, style = MaterialTheme.typography.bodyLarge)
                    Spacer(Modifier.height(16.dp))
                    Text("Recompensas:")
                    Row {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(Color.Yellow, RoundedCornerShape(8.dp))
                                    .padding(8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("x$it")
                            }
                        }
                    }
                    Button(
                        onClick = { /* reclamar */ },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text("Reclamar")
                    }
                }
            }
        }
    )
}