package com.peacemaker.service.auth

import com.peacemaker.models.LoginUser
import com.peacemaker.models.RegisterUser
import com.peacemaker.models.ResetPassword
import com.peacemaker.models.User

interface AuthService {
    /**
     * @author Peacemaker Otoo
     * This class handle functions for the user services
     * */

    /**Register user function to the user class*/
    suspend fun registerUser(params: RegisterUser): User?

    /**Login user function to the user class*/
    suspend fun loginUser(params: LoginUser):User?

    /**find user by [email] for validation if user already exist*/
    suspend fun findUserByEmail(email: String): User?

    /**check if password exist: Select by [email] where UserTable.password eq [password] */
    suspend fun ifPasswordCorrect(email: String, password: String):User?

    /**
     * For us to update the user password, we need select and insert.
     * Thus, first select email as an id and update the corresponding password
     * @param[params.email] : Find user by email and
     * @param[params.password]: update the user password
     * */
    suspend fun resetPassword(params: ResetPassword):User?


}