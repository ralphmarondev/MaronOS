package com.ralphmarondev.data.local.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fullName: String,
    val username: String,
    val password: String,
    val createDate: Long = System.currentTimeMillis(),
    val isDeleted: Boolean = false
)