package com.example.myapp.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val etiquetas: List<String>, // Converter
    val idUsuario: String,
    val dinero: Int, // ahora es Int
    val imagenUri: String? = null // se puede calcular con un JOIN con personaje
)