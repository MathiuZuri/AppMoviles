package com.example.myapp.data.personaje

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "personajes")
data class PersonajeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imagenUrl: String,
    val vida: Int,
    val velocidad: Int,
    val dano: Int,
    val nombre: String,
    val descripcion: String,
    val costo: Int
)