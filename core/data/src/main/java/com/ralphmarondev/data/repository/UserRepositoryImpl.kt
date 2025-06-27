package com.ralphmarondev.data.repository

import com.ralphmarondev.data.local.database.dao.UserDao
import com.ralphmarondev.data.mapper.toDomain
import com.ralphmarondev.data.mapper.toEntity
import com.ralphmarondev.domain.model.User
import com.ralphmarondev.domain.repository.UserRepository

class UserRepositoryImpl(
    private val dao: UserDao
) : UserRepository {
    override suspend fun create(user: User) {
        dao.create(user.toEntity())
    }

    override suspend fun update(user: User) {
        dao.update(user.toEntity())
    }

    override suspend fun delete(id: Long) {
        dao.delete(id)
    }

    override suspend fun getDetailByUsername(username: String): User? {
        return dao.getDetailByUsername(username)?.toDomain()
    }
}