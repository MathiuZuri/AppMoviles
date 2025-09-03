package com.example.myapp.data.inventario

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy // <-- This is the missing import
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface InventarioDao {

    // Insertar un registro en inventario
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInventario(inventario: InventarioEntity)

    // Eliminar un registro
    @Delete
    suspend fun deleteInventario(inventario: InventarioEntity)

    // Obtener todo el inventario de un usuario
    @Query("SELECT * FROM inventario WHERE idUsuario = :idUsuario")
    fun getInventarioByUsuario(idUsuario: Int): Flow<List<InventarioEntity>>

    // Verificar si un personaje está en inventario de un usuario
    @Query("SELECT COUNT(*) FROM inventario WHERE idUsuario = :idUsuario AND idPersonaje = :idPersonaje")
    suspend fun exists(idUsuario: Int, idPersonaje: Int): Int

    // Opcional: limpiar inventario de un usuario
    @Query("DELETE FROM inventario WHERE idUsuario = :idUsuario")
    suspend fun clearInventarioUsuario(idUsuario: Int)
}
