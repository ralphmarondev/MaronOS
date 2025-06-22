package com.ralphmarondev.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ralphmarondev.data.local.database.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun create(note: NoteEntity)

    @Update
    suspend fun update(note: NoteEntity)

    @Query("UPDATE notes SET isDeleted = 1 WHERE id = :id AND isDeleted = 0")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM notes WHERE isDeleted = 0")
    fun getAll(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM notes WHERE id = :id AND isDeleted = 0 LIMIT 1")
    suspend fun getById(id: Long): NoteEntity
}