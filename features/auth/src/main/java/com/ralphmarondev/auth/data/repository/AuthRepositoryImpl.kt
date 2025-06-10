package com.ralphmarondev.auth.data.repository

import com.ralphmarondev.auth.domain.repository.AuthRepository
import com.ralphmarondev.domain.model.Result

class AuthRepositoryImpl : AuthRepository {
    override suspend fun login(
        username: String,
        password: String
    ): Result {
        return if (username == "root" && password == "toor") {
            Result(
                success = true,
                message = "Login successful."
            )
        } else {
            Result(
                success = false,
                message = "Login failed."
            )
        }
    }
}