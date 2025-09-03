package com.example.myapp.data.personaje

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonajeDao {
    @Insert
    suspend fun insertPersonaje(personaje: PersonajeEntity): Long

    @Insert
    suspend fun insertAll(personajes: List<PersonajeEntity>)

    @Update
    suspend fun updatePersonaje(personaje: PersonajeEntity)

    @Delete
    suspend fun deletePersonaje(personaje: PersonajeEntity)

    @Query("SELECT * FROM personajes WHERE id = :id LIMIT 1")
    suspend fun getPersonajeById(id: Int): PersonajeEntity?

    @Query("SELECT * FROM personajes")
    fun getAllPersonajes(): Flow<List<PersonajeEntity>>
}