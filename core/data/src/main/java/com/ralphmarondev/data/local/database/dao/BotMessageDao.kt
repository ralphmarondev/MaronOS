package com.ralphmarondev.data.local.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.ralphmarondev.data.local.database.entity.BotMessageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BotMessageDao {

    suspend fun create(botMessageEntity: BotMessageEntity)

    @Query("SELECT * FROM bot_message")
    fun getAll(): Flow<List<BotMessageEntity>>
}