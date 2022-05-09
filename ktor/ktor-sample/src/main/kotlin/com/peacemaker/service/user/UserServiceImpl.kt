package com.peacemaker.service.user

import com.peacemaker.db.DatabaseFactory.dbQuery
import com.peacemaker.db.extensions.toUser
import com.peacemaker.db.schema.UserTable
import com.peacemaker.models.User
import org.jetbrains.exposed.sql.select

class UserServiceImpl : UserService {
    override suspend fun getUser(id: Int): User {
        val userRow = dbQuery { UserTable.select { UserTable.id eq id }.first() }
        return userRow.toUser()!!
    }
}