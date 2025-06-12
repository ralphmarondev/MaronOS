package com.ralphmarondev.domain.usecase.user

import com.ralphmarondev.domain.model.Result
import com.ralphmarondev.domain.repository.UserRepository

class LoginUseCase(
    private val repository: UserRepository
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
            val user = repository.getDetailByUsername(username)
            if (user?.id != null && user.password == password) {
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

        } catch (e: Exception) {
            Result(
                success = false,
                message = "Unexpected error occurred. Error: ${e.message}"
            )
        }
    }
}