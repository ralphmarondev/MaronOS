package com.ralphmarondev.auth.domain.repository

import com.ralphmarondev.domain.model.Result

interface AuthRepository {
    suspend fun login(username: String, password: String): Result
}