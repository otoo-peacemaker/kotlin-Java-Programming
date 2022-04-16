package com.peacemaker.repository

import com.peacemaker.service.LoginUser
import com.peacemaker.util.BaseResponse
import com.peacemaker.service.RegisterUser
import com.peacemaker.service.ResetPassword

interface UserRepository {
    /**For Repository to make web request to interact with the database*/

    suspend fun registerUser(params: RegisterUser): BaseResponse<Any>?
    suspend fun loginUser(params: LoginUser): BaseResponse<Any>?
    suspend fun resetUserPassword(params: ResetPassword): BaseResponse<Any>?

}