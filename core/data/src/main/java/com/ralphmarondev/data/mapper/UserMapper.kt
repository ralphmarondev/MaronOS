package com.ralphmarondev.data.mapper

import com.ralphmarondev.data.local.database.entity.UserEntity
import com.ralphmarondev.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        fullName = fullName,
        username = username,
        password = password
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        fullName = fullName,
        username = username,
        password = password
    )
}