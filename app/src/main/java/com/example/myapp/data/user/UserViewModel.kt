package com.example.myapp.data.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableStateFlow<UserEntity?>(null)
    val user: StateFlow<UserEntity?> = _user

    fun loadUser(idUsuario: String) {
        viewModelScope.launch {
            val loaded = repository.getUser(idUsuario)
            if (loaded != null) {
                _user.value = loaded
            } else {
                // 🔹 Si no existe, creamos usuario nuevo por defecto
                val newUser = UserEntity(
                    nombre = "Jugador",
                    descripcion = "Nuevo usuario",
                    etiquetas = listOf("Novato"),
                    idUsuario = "123456789",
                    imagenUri = "",
                    dinero = 100.0
                )
                repository.insertUser(newUser)
                _user.value = newUser
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
            repository.updateUser(updated)
            _user.value = updated
        }
    }
}