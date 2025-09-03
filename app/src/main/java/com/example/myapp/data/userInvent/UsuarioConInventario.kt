package com.example.myapp.data.userInvent

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.myapp.data.inventario.InventarioEntity
import com.example.myapp.data.personaje.PersonajeEntity
import com.example.myapp.data.user.UserEntity

data class UsuarioConInventario(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = InventarioEntity::class,
            parentColumn = "idUsuario",
            entityColumn = "idPersonaje"
        )
    )
    val personajes: List<PersonajeEntity>
)