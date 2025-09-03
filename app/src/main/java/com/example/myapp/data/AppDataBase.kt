package com.example.myapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapp.data.inventario.InventarioDao
import com.example.myapp.data.user.UserDao
import com.example.myapp.data.user.UserEntity
import com.example.myapp.data.inventario.InventarioEntity
import com.example.myapp.data.personaje.PersonajeDao
import com.example.myapp.data.personaje.PersonajeEntity
import com.example.myapp.util.Converters

@Database(
    entities = [
        UserEntity::class,
        PersonajeEntity::class,
        InventarioEntity::class
    ],
    version = 2, // subimos a 2 porque cambiaste esquema
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun personajeDao(): PersonajeDao
    abstract fun inventarioDao(): InventarioDao

}

object DatabaseProvider {
    @Volatile
    private var INSTANCE: AppDatabase? = null

    fun getDatabase(context: Context): AppDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "gameDB" // usa siempre el mismo nombre
            )
                .fallbackToDestructiveMigration() // elimina y recrea si cambia versión
                .build()
            INSTANCE = instance
            instance
        }
    }
}


