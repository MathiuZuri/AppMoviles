package com.example.myapp.data.inventario

import kotlinx.coroutines.flow.Flow

class InventarioRepository(private val inventarioDao: InventarioDao) {

    fun getInventarioByUsuario(idUsuario: Int): Flow<List<InventarioEntity>> =
        inventarioDao.getInventarioByUsuario(idUsuario)

    suspend fun insertInventario(inventario: InventarioEntity) {
        inventarioDao.insertInventario(inventario)
    }

    suspend fun deleteInventario(inventario: InventarioEntity) {
        inventarioDao.deleteInventario(inventario)
    }

    suspend fun exists(idUsuario: Int, idPersonaje: Int): Boolean =
        inventarioDao.exists(idUsuario, idPersonaje) > 0

    suspend fun clearInventarioUsuario(idUsuario: Int) {
        inventarioDao.clearInventarioUsuario(idUsuario)
    }
}