package com.example.myapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaInicio(navController: NavController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Usamos ModalNavigationDrawer
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Forzamos que el drawer aparezca a la derecha
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentWidth(Alignment.End) // alineación derecha
            ) {
                ModalDrawerSheet(
                    modifier = Modifier
                        .width(250.dp) // ancho fijo del panel
                        .fillMaxHeight()
                        .align(Alignment.TopEnd)
                ) {
                    Text(
                        "Menú",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                    NavigationDrawerItem(
                        label = { Text("Configuración") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("configuracion")
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Registro de partidas") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("registroPartidas")
                        }
                    )
                    NavigationDrawerItem(
                        label = { Text("Buzón") },
                        selected = false,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate("buzon")
                        }
                    )
                }
            }
        }
    ) {
        // Contenido principal
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                ) {
                    // Saldo + menú
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "Saldo")
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("$$$$$$", style = MaterialTheme.typography.bodyLarge)
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menú",
                            modifier = Modifier.clickable {
                                scope.launch { drawerState.open() }
                            }
                        )
                    }

                    // Izquierda: botones verticales pequeños
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Button(onClick = { navController.navigate("tienda") }) {
                            Text("Tienda")
                        }
                        Button(onClick = { navController.navigate("personajes") }) {
                            Text("Personajes")
                        }
                    }

                    // Derecha: botones verticales grandes
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Button(
                            onClick = { navController.navigate("mapas") },
                            modifier = Modifier
                                .width(150.dp)
                                .height(60.dp)
                        ) { Text("Mapas") }

                        Button(
                            onClick = { navController.navigate("modos") },
                            modifier = Modifier
                                .width(150.dp)
                                .height(60.dp)
                        ) { Text("Modos") }

                        Button(
                            onClick = { navController.navigate("jugar") },
                            modifier = Modifier
                                .width(150.dp)
                                .height(60.dp)
                        ) { Text("Jugar") }
                    }
                }
            }
        )
    }
}

