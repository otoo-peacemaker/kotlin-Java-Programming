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

    /**check if password exist*/
    suspend fun ifPasswordCorrect(password: String):User?

    suspend fun loginUser(params:LoginUser):User?


    /**
     * For us to update the user password, we need select and insert.
     * Thus, first select email as an id and update the corresponding password
     * @param[email] : Find user by email and
     * @param[password]: update the user password
     * */
    suspend fun resetPassword(params: ResetPassword):User?


}