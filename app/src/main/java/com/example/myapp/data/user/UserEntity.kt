package com.example.myapp.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val etiquetas: List<String>, // ahora ya funciona con el Converter
    val idUsuario: String,
    val dinero: Double,
    val imagenUri: String? = null
)