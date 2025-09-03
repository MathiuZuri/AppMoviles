package com.example.myapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapp.data.user.UserDao
import com.example.myapp.data.user.UserEntity
import com.example.myapp.util.Converters

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false // o false si no quieres generar schema
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}