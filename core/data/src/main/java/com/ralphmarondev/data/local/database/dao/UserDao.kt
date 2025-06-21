package com.ralphmarondev.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ralphmarondev.data.local.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(user: UserEntity)

    @Update
    suspend fun update(user: UserEntity)

    @Query("UPDATE users SET isDeleted = 1 WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM users WHERE username = :username AND isDeleted = 0 LIMIT 1")
    suspend fun getDetailByUsername(username: String): UserEntity?
}