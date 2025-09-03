package com.example.myapp.data.personaje

import kotlinx.coroutines.flow.Flow

class PersonajeRepository(private val personajeDao: PersonajeDao) {

    fun getAllPersonajes(): Flow<List<PersonajeEntity>> =
        personajeDao.getAllPersonajes()

    suspend fun getPersonajeById(id: Int): PersonajeEntity? =
        personajeDao.getPersonajeById(id)

    suspend fun insertPersonaje(personaje: PersonajeEntity): Long {
        return personajeDao.insertPersonaje(personaje)
    }

    suspend fun insertAll(personajes: List<PersonajeEntity>) {
        personajeDao.insertAll(personajes)
    }

    suspend fun updatePersonaje(personaje: PersonajeEntity) {
        personajeDao.updatePersonaje(personaje)
    }

    suspend fun deletePersonaje(personaje: PersonajeEntity) {
        personajeDao.deletePersonaje(personaje)
    }
}
