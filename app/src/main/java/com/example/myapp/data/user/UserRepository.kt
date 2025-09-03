package com.example.myapp.data.user


class UserRepository(private val userDao: UserDao) {
    suspend fun getUser(id: String): UserEntity? = userDao.getUser(id)
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    suspend fun updateUser(user: UserEntity) = userDao.updateUser(user)
}