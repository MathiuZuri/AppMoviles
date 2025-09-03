package com.example.myapp.data.inventario

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InventarioViewModel(
    private val repository: InventarioRepository
) : ViewModel() {

    private val _inventario = MutableStateFlow<List<InventarioEntity>>(emptyList())
    val inventario: StateFlow<List<InventarioEntity>> = _inventario

    // Cargar inventario de un usuario
    fun loadInventarioUsuario(idUsuario: Int) {
        viewModelScope.launch {
            repository.getInventarioByUsuario(idUsuario).collect { lista ->
                _inventario.value = lista
            }
        }
    }

    // Insertar personaje en inventario
    fun addPersonajeToInventario(idUsuario: Int, idPersonaje: Int) {
        viewModelScope.launch {
            repository.insertInventario(InventarioEntity(idUsuario, idPersonaje))
        }
    }

    // Eliminar personaje del inventario
    fun removePersonajeFromInventario(idUsuario: Int, idPersonaje: Int) {
        viewModelScope.launch {
            repository.deleteInventario(InventarioEntity(idUsuario, idPersonaje))
        }
    }

    // Verificar si personaje está en inventario
    suspend fun personajeEnInventario(idUsuario: Int, idPersonaje: Int): Boolean {
        return repository.exists(idUsuario, idPersonaje)
    }

    // Limpiar inventario completo del usuario
    fun clearInventarioUsuario(idUsuario: Int) {
        viewModelScope.launch {
            repository.clearInventarioUsuario(idUsuario)
        }
    }
}