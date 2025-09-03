package com.example.myapp.data.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.data.inventario.InventarioEntity
import com.example.myapp.data.inventario.InventarioRepository
import com.example.myapp.data.personaje.PersonajeEntity
import com.example.myapp.data.personaje.PersonajeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(
    private val userRepository: UserRepository,
    private val personajeRepository: PersonajeRepository,
    private val inventarioRepository: InventarioRepository
) : ViewModel() {

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user: StateFlow<UserEntity?> = _user

    fun loadUser(idUsuario: String) {
        viewModelScope.launch {
            val loaded = userRepository.getUser(idUsuario)
            if (loaded != null) {
                _user.value = loaded
            } else {
                // Crear usuario nuevo
                val newUser = UserEntity(
                    nombre = "Jugador",
                    descripcion = "Nuevo usuario",
                    etiquetas = listOf("Novato"),
                    idUsuario = "123456789",
                    imagenUri = "",
                    dinero = 100
                )

                val userId = userRepository.insertUser(newUser).toInt() // 🔹 obtenemos id autogenerado

                // Crear personaje inicial (si no existe ninguno)
                val personajeInicial = PersonajeEntity(
                    imagenUrl = "file:///android_asset/Hulls_Color_A/Hull_01.png",
                    vida = 120,
                    velocidad = 10,
                    dano = 25,
                    nombre = "Guerrero",
                    descripcion = "Un luchador fuerte y resistente.",
                    costo = 100
                )
                val personajeId = personajeRepository.insertPersonaje(personajeInicial).toInt()

                // Relación en inventario
                val inventario = InventarioEntity(
                    idUsuario = userId,
                    idPersonaje = personajeId
                )
                inventarioRepository.insertInventario(inventario)

                // Actualizamos StateFlow con usuario
                _user.value = newUser.copy(id = userId)
            }
        }
    }

    fun updateUser(nombre: String, descripcion: String, etiquetas: List<String>, imagen: String?) {
        val current = _user.value ?: return
        val updated = current.copy(
            nombre = nombre,
            descripcion = descripcion,
            etiquetas = etiquetas,
            imagenUri = imagen
        )
        viewModelScope.launch {
            userRepository.updateUser(updated)
            _user.value = updated
        }
    }
}