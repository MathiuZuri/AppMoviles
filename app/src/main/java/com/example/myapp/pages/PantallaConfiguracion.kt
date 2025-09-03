package com.example.myapp.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myapp.data.user.UserViewModel

// Lista de etiquetas predefinidas
val etiquetasPredefinidas = listOf(
    "Novato", "Aspirante", "Veterano", "Maestro", "Leyenda",
    "Estratega", "Explorador Incansable", "Primer Paso",
    "Rey de la Colina", "Solo por Diversión", "Inmortal",
    "Rompe Récords"
)

/** Helper que convierte etiquetas (String "a,b,c" o List<String>) en List<String> */
private fun toEtiquetasListFlexible(etiquetas: Any?): List<String> = when (etiquetas) {
    is String -> etiquetas
        .split(",")
        .map { it.trim() }
        .filter { it.isNotEmpty() }
    is List<*> -> etiquetas.filterIsInstance<String>()
    else -> emptyList()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PantallaConfiguracion(
    navController: NavController,
    userViewModel: UserViewModel
) {
    val user by userViewModel.user.collectAsState()
    val focusManager = LocalFocusManager.current

    // Cargar siempre el usuario con id "1" (no hay login)
    LaunchedEffect(Unit) { userViewModel.loadUser("1") }

    // Estados locales para edición
    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var descripcion by remember { mutableStateOf(TextFieldValue("")) }
    var etiquetasSeleccionadas by remember { mutableStateOf(listOf<String>()) }
    var editando by remember { mutableStateOf(false) }
    var mostrarGuardar by remember { mutableStateOf(false) }

    // Inicializa los campos cuando llega el user
    LaunchedEffect(user) {
        user?.let { u ->
            nombre = TextFieldValue(u.nombre)
            descripcion = TextFieldValue(u.descripcion)
            etiquetasSeleccionadas = toEtiquetasListFlexible(u.etiquetas)
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { focusManager.clearFocus() },
        content = { innerPadding ->
            Box(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp)
                ) {
                    // Lado izquierdo
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.Top
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                        }
                        Spacer(Modifier.height(16.dp))

                        if (editando) {
                            OutlinedTextField(
                                value = nombre,
                                onValueChange = {
                                    nombre = it
                                    mostrarGuardar = true
                                },
                                label = { Text("Nombre de usuario") },
                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(
                                    onDone = { focusManager.clearFocus() }
                                )
                            )
                        } else {
                            Text(nombre.text, style = MaterialTheme.typography.bodyLarge)
                        }

                        Spacer(Modifier.height(16.dp))

                        if (editando) {
                            OutlinedTextField(
                                value = descripcion,
                                onValueChange = {
                                    descripcion = it
                                    mostrarGuardar = true
                                },
                                label = { Text("Descripción del usuario") },
                                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(
                                    onDone = { focusManager.clearFocus() }
                                )
                            )
                        } else {
                            Text(descripcion.text, style = MaterialTheme.typography.bodyMedium)
                        }
                    }

                    // Centro: imagen usuario
                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Usuario",
                            modifier = Modifier.size(120.dp)
                        )
                    }

                    // Derecha: estadísticas + etiquetas con scroll
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.SpaceBetween // 👈 asegura posición del botón
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .verticalScroll(rememberScrollState()) // 👈 scroll solo para etiquetas
                        ) {
                            Text("Etiquetas:", style = MaterialTheme.typography.bodySmall)

                            if (editando) {
                                MultiSelectEtiquetas(
                                    seleccionadas = etiquetasSeleccionadas,
                                    onChange = {
                                        etiquetasSeleccionadas = it
                                        mostrarGuardar = true
                                    }
                                )
                            } else {
                                etiquetasSeleccionadas.forEach { InfoBox(it) }
                            }

                            Spacer(Modifier.height(8.dp))
                            InfoBox("IdUsuario: ${user?.idUsuario ?: "1"}")
                        }

                        Spacer(Modifier.height(8.dp))
                        Button(onClick = { editando = !editando }) {
                            Text(if (editando) "Cancelar" else "Editar")
                        }
                    }
                }

                // Botón Guardar centrado
                if (mostrarGuardar && editando) {
                    Button(
                        onClick = {
                            userViewModel.updateUser(
                                nombre = nombre.text,
                                descripcion = descripcion.text,
                                etiquetas = etiquetasSeleccionadas,
                                imagen = user?.imagenUri
                            )
                            editando = false
                            mostrarGuardar = false
                        },
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(16.dp)
                    ) {
                        Text("Guardar")
                    }
                }
            }
        }
    )
}

@Composable
fun MultiSelectEtiquetas(
    seleccionadas: List<String>,
    onChange: (List<String>) -> Unit
) {
    Column {
        etiquetasPredefinidas.forEach { etiqueta ->
            val isSelected = etiqueta in seleccionadas
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val nuevas = if (isSelected) {
                            seleccionadas - etiqueta
                        } else {
                            seleccionadas + etiqueta
                        }
                        onChange(nuevas)
                    }
                    .padding(4.dp)
            ) {
                Checkbox(
                    checked = isSelected,
                    onCheckedChange = { checked ->
                        val nuevas = if (checked) {
                            seleccionadas + etiqueta
                        } else {
                            seleccionadas - etiqueta
                        }
                        onChange(nuevas)
                    }
                )
                Text(etiqueta)
            }
        }
    }
}

@Composable
fun InfoBox(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray, RoundedCornerShape(8.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Default.Check, contentDescription = null)
        Spacer(Modifier.width(8.dp))
        Text(text)
    }
}
