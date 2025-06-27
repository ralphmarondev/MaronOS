package com.ralphmarondev.domain.repository

import com.ralphmarondev.domain.model.User

interface UserRepository {

    suspend fun create(user: User)

    suspend fun update(user: User)

    suspend fun delete(id: Long)

    suspend fun getDetailByUsername(username: String): User?
}