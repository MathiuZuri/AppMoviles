package com.example.myapp.data.personaje

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import coil.compose.rememberAsyncImagePainter

class PersonajeViewModel(
    private val repository: PersonajeRepository

) : ViewModel() {

    // Lista de personajes observable
    val personajes: StateFlow<List<PersonajeEntity>> =
        repository.getAllPersonajes()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Guardar personaje
    fun addPersonaje(personaje: PersonajeEntity) {
        viewModelScope.launch {
            repository.insertPersonaje(personaje)
        }
    }

    // Guardar varios personajes (ej: preload inicial)
    fun addAllPersonajes(lista: List<PersonajeEntity>) {
        viewModelScope.launch {
            repository.insertAll(lista)
        }
    }

    // Actualizar
    fun updatePersonaje(personaje: PersonajeEntity) {
        viewModelScope.launch {
            repository.updatePersonaje(personaje)
        }
    }

    // Eliminar
    fun deletePersonaje(personaje: PersonajeEntity) {
        viewModelScope.launch {
            repository.deletePersonaje(personaje)
        }
    }

    // Buscar por ID
    suspend fun getPersonajeById(id: Int): PersonajeEntity? {
        return repository.getPersonajeById(id)
    }



    @Composable
    fun preloadedPersonajes(context: Context): List<PersonajeEntity> {

        Image(
            painter = rememberAsyncImagePainter("file:///android_asset/Hulls_Color_A/Hull_01.png"),
            contentDescription = "Guerrero"


        )

        return listOf(
            PersonajeEntity(
                id = 1,
                imagenUrl = "file:///android_asset/Hulls_Color_A/Hull_01.png",
                vida = 120,
                velocidad = 10,
                dano = 25,
                nombre = "Guerrero",
                descripcion = "Un luchador fuerte y resistente.",
                costo = 100
            ),
            PersonajeEntity(
                imagenUrl = "android.resource://${context.packageName}/assets/Hulls_Color_A",
                vida = 80,
                velocidad = 15,
                dano = 20,
                nombre = "Arquero",
                descripcion = "Ataca desde la distancia con gran precisión.",
                costo = 120
            ),
            PersonajeEntity(
                imagenUrl = "android.resource://${context.packageName}/drawable/mage",
                vida = 60,
                velocidad = 12,
                dano = 35,
                nombre = "Mago",
                descripcion = "Usa hechizos poderosos para derrotar enemigos.",
                costo = 150
            ),
            PersonajeEntity(
                imagenUrl = "android.resource://${context.packageName}/drawable/assassin",
                vida = 70,
                velocidad = 20,
                dano = 40,
                nombre = "Asesino",
                descripcion = "Rápido y letal, especializado en ataques críticos.",
                costo = 180
            ),
            PersonajeEntity(
                imagenUrl = "android.resource://${context.packageName}/drawable/tank",
                vida = 200,
                velocidad = 8,
                dano = 15,
                nombre = "Tanque",
                descripcion = "Soporta mucho daño para proteger al equipo.",
                costo = 200
            ),
            PersonajeEntity(
                imagenUrl = "android.resource://${context.packageName}/drawable/healer",
                vida = 90,
                velocidad = 10,
                dano = 10,
                nombre = "Sanador",
                descripcion = "Mantiene con vida al equipo curando sus heridas.",
                costo = 160
            )
        )
    }

}
