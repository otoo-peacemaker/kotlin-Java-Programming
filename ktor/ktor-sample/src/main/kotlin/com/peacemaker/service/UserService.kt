package com.peacemaker.service

import com.peacemaker.models.User

interface UserService {
    /**
     * @author Peacemaker Otoo
     * This class handle functions for the user services
     * */


    /**Register user function to the user class*/
    suspend fun registerUser(params:RegisterUser): User?

    /**find user by email for validation if user already exist*/
    suspend fun findUserByEmail(email: String): User?
}