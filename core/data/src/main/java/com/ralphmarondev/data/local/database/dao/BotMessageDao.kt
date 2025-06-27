package com.ralphmarondev.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ralphmarondev.data.local.database.entity.BotMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BotMessageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(botMessageEntity: BotMessageEntity)

    @Query("SELECT * FROM bot_message")
    fun getAll(): Flow<List<BotMessageEntity>>
}