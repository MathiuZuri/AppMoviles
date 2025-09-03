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
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.pages.*
import com.example.myapp.ui.theme.MyAppTheme
import com.example.myapp.data.AppDatabase
import com.example.myapp.data.DatabaseProvider
import com.example.myapp.data.user.UserRepository
import com.example.myapp.data.personaje.PersonajeRepository
import com.example.myapp.data.inventario.InventarioRepository
import com.example.myapp.data.user.UserViewModel
import com.example.myapp.data.UserViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = DatabaseProvider.getDatabase(applicationContext)

        // 🔹 Instanciar repositorios
        val userRepository = UserRepository(db.userDao())
        val personajeRepository = PersonajeRepository(db.personajeDao())
        val inventarioRepository = InventarioRepository(db.inventarioDao())

        // 🔹 Usar el Factory para crear el ViewModel
        val userViewModel = ViewModelProvider(
            this,
            UserViewModelFactory(userRepository, personajeRepository, inventarioRepository)
        )[UserViewModel::class.java]

        setContent {
            MyAppTheme {
                AppNavigation(userViewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(userViewModel: UserViewModel) {
    val navController = rememberNavController()
    userViewModel.loadUser("123456789")

    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { PantallaInicio(navController) }
        composable("tienda") { ShopPage(navController) }
        composable("personajes") { CharactersPage(navController) }
        composable("personajeDetalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            CharacterDetailPage(id, navController)
        }
        composable("mapas") { MapsPage(navController) }
        composable("modos") { ModosPage(navController) }
        composable("jugar") { PantallaGenerica("Jugar", navController) }
        composable("configuracion") { PantallaConfiguracion(navController, userViewModel) }
        composable("registroPartidas") { PantallaRegistroPartidas(navController) }
        composable("buzon") { PantallaBuzon(navController) }
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
