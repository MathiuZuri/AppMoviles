package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapp.pages.CharacterDetailPage
import com.example.myapp.pages.CharactersPage
import com.example.myapp.pages.MapsPage
import com.example.myapp.pages.ModosPage
import com.example.myapp.pages.ShopPage
import com.example.myapp.ui.theme.MyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { PantallaInicio(navController) }
        composable("tienda") { ShopPage(navController) } //Navigate de pagina tienda
        composable("personajes") { CharactersPage(navController) } // Navigate a personajes
        composable("personajeDetalle/{id}") { backStackEntry -> //Navigate a detalles de personaje
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            CharacterDetailPage(id, navController) // detalles
        }
        composable("mapas") { MapsPage(navController) }//Navigate de pagina mapas
        composable("modos") { ModosPage(navController) }//Navigate de pagina modos
        composable("jugar") { PantallaGenerica("Jugar", navController) }
    }
}

@Composable
fun PantallaInicio(navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                // Esquina superior derecha: saldo e iconos
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
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menú")
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
                        modifier = Modifier.width(150.dp).height(60.dp)
                    ) {
                        Text("Mapas")
                    }
                    Button(
                        onClick = { navController.navigate("modos") },
                        modifier = Modifier.width(150.dp).height(60.dp)
                    ) {
                        Text("Modos")
                    }
                    Button(
                        onClick = { navController.navigate("jugar") },
                        modifier = Modifier.width(150.dp).height(60.dp)
                    ) {
                        Text("Jugar")
                    }
                }
            }
        }
    )
}

@Composable
fun PantallaGenerica(nombre: String, navController: NavController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("Pantalla: $nombre", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(20.dp))
                Button(onClick = { navController.popBackStack() }) {
                    Text("Regresar")
                }
            }
        }
    )
}

@Preview(showBackground = true, widthDp = 800, heightDp = 400)
@Composable
fun PreviewPantallaInicio() {
    MyAppTheme {
        PantallaInicio(rememberNavController())
    }
}
