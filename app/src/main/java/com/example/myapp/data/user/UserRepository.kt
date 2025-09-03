package com.example.myapp.data.user
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class UserRepository(private val dao: UserDao) {
    fun observeUser(idUsuario: String): Flow<UserEntity?> = dao.observeUser(idUsuario)

    suspend fun getUser(idUsuario: String): UserEntity? = dao.observeUser(idUsuario).first()
    suspend fun insertUser(user: UserEntity): Long = dao.insertUser(user)
    suspend fun updateUser(user: UserEntity) = dao.updateUser(user)
}