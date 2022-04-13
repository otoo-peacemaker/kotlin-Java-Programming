package com.peacemaker.repository

import com.peacemaker.BaseResponse.BaseResponse
import com.peacemaker.service.RegisterUser

class UserRepositoryImpl : UserRepository {
    /**For Repository to make web request*/
    override suspend fun registerUser(params: RegisterUser): BaseResponse<Any>? {
        TODO("Not yet implemented")
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any>? {
        TODO("Not yet implemented")
    }
}