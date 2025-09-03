package com.example.myapp.data.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE idUsuario = :idUsuario LIMIT 1")
    fun observeUser(idUsuario: String): Flow<UserEntity?>

    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Update
    suspend fun updateUser(user: UserEntity)
}