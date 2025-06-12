package com.ralphmarondev.domain.model

data class User(
    val id: Int = 0,
    val fullName: String,
    val username: String,
    val password: String
)