package com.peacemaker.repository

import com.peacemaker.util.BaseResponse
import com.peacemaker.service.RegisterUser

interface UserRepository {
    /**For Repository to make web request*/

    suspend fun registerUser(params: RegisterUser): BaseResponse<Any>?
    suspend fun loginUser(email: String, password: String): BaseResponse<Any>?

}