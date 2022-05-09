package com.peacemaker.repository.auths

import com.peacemaker.models.LoginUser
import com.peacemaker.util.BaseResponse
import com.peacemaker.models.RegisterUser
import com.peacemaker.models.ResetPassword

interface AuthRepository {
    /**For Repository to make web request to interact with the database*/

    suspend fun registerUser(params: RegisterUser): BaseResponse<Any>?
    suspend fun loginUser(params: LoginUser): BaseResponse<Any>?
    suspend fun resetUserPassword(params: ResetPassword): BaseResponse<Any>?

}