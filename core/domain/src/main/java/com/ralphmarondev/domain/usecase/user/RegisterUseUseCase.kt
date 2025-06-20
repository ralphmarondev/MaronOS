package com.ralphmarondev.domain.usecase.user

import com.ralphmarondev.domain.model.Result
import com.ralphmarondev.domain.model.User
import com.ralphmarondev.domain.repository.UserRepository

class RegisterUseUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Result {

        if (user.fullName.isBlank()) {
            return Result(
                success = false,
                message = "Full name cannot be empty."
            )
        }
        if (user.username.isBlank()) {
            return Result(
                success = false,
                message = "Username cannot be empty."
            )
        }
        if (user.password.isBlank()) {
            return Result(
                success = false,
                message = "Password cannot be empty."
            )
        }

        return try {
            repository.create(user)
            Result(
                success = true,
                message = "User registered successfully."
            )
        } catch (e: Exception) {
            Result(
                success = false,
                message = "User registration failed. Error: ${e.message}"
            )
        }
    }
}