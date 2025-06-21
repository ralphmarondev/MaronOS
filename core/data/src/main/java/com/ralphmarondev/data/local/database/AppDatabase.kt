package com.ralphmarondev.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ralphmarondev.data.local.database.dao.UserDao
import com.ralphmarondev.data.local.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        private const val NAME = "maron_os_database"

        fun createDatabase(context: Context): AppDatabase {
            try {
                val database = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    NAME
                ).build()
                return database
            } catch (e: Exception) {
                throw e
            }
        }
    }
}