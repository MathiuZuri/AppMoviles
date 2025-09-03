package com.example.myapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
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
import com.example.myapp.pages.PantallaBuzon
import com.example.myapp.pages.PantallaConfiguracion
import com.example.myapp.pages.PantallaInicio
import com.example.myapp.pages.PantallaRegistroPartidas
import com.example.myapp.pages.ShopPage
import com.example.myapp.ui.theme.MyAppTheme
import com.example.myapp.data.user.UserViewModel
import androidx.room.Room
import com.example.myapp.data.AppDatabase
import com.example.myapp.data.user.UserRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "gameDb"
        ).build()

        val repository = UserRepository(db.userDao())
        val userViewModel = UserViewModel(repository)

        setContent {
            MyAppTheme {
                AppNavigation(userViewModel) // 🔹 pásalo al navigation
            }
        }
    }
}

@Composable
fun AppNavigation(userViewModel: UserViewModel) {
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
        composable("jugar") { PantallaGenerica("Jugar", navController) }//navigate ejemplo
        composable("configuracion") { PantallaConfiguracion(navController, userViewModel) }//navigate configuracion
        composable("registroPartidas") { PantallaRegistroPartidas(navController) }//navigate registro partidas
        composable("buzon") { PantallaBuzon(navController) }//navigate buzon
    }
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
