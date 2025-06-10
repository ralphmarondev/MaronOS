package com.ralphmarondev.auth.domain.usecase

import com.ralphmarondev.auth.domain.repository.AuthRepository
import com.ralphmarondev.domain.model.Result

class LoginUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result {
        if (username.isBlank() && password.isBlank()) {
            return Result(
                success = false,
                message = "Username and password cannot be empty."
            )
        }
        if (username.isBlank()) {
            return Result(
                success = false,
                message = "Username cannot be empty."
            )
        }
        if (password.isBlank()) {
            return Result(
                success = false,
                message = "Password cannot be empty."
            )
        }

        return try {
            repository.login(username, password)
        } catch (e: Exception) {
            Result(
                success = false,
                message = "Unexpected error occurred. Error: ${e.message}"
            )
        }
    }
}