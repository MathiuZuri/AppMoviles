package com.example.myapp.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapp.data.inventario.InventarioRepository
import com.example.myapp.data.personaje.PersonajeRepository
import com.example.myapp.data.user.UserRepository
import com.example.myapp.data.user.UserViewModel

class UserViewModelFactory(
    private val userRepository: UserRepository,
    private val personajeRepository: PersonajeRepository,
    private val inventarioRepository: InventarioRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userRepository, personajeRepository, inventarioRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}