package com.example.myapp.data.inventario

import androidx.room.Entity
import androidx.room.ForeignKey // Importa la clase ForeignKey
import androidx.room.Index // Importa la clase Index
import com.example.myapp.data.user.UserEntity
import com.example.myapp.data.personaje.PersonajeEntity

@Entity(
    tableName = "inventario",
    primaryKeys = ["idUsuario", "idPersonaje"],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["idUsuario"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PersonajeEntity::class,
            parentColumns = ["id"],
            childColumns = ["idPersonaje"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("idUsuario"), Index("idPersonaje")]
)
data class InventarioEntity(
    val idUsuario: Int,
    val idPersonaje: Int
)