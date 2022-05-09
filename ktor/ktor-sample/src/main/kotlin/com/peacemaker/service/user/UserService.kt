package com.peacemaker.service.user

import com.peacemaker.models.User

interface UserService {
    suspend fun getUser(id: Int): User
}