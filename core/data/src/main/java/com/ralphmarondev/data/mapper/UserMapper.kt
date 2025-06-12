package com.ralphmarondev.data.mapper

import com.ralphmarondev.data.local.database.entity.User

fun User.toDomain(): com.ralphmarondev.domain.model.User {
    return com.ralphmarondev.domain.model.User(
        id = id,
        fullName = fullName,
        username = username,
        password = password
    )
}

fun com.ralphmarondev.domain.model.User.toEntity(): User {
    return User(
        id = id,
        fullName = fullName,
        username = username,
        password = password
    )
}