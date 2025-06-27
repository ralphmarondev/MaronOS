package com.ralphmarondev.domain.model

data class User(
    val id: Long = 0,
    val fullName: String,
    val username: String,
    val password: String
)