package com.peacemaker.repository.auths

import com.peacemaker.models.LoginUser
import com.peacemaker.util.BaseResponse
import com.peacemaker.models.RegisterUser
import com.peacemaker.models.ResetPassword

/**
 * Auth repository
 *
 * @constructor Create empty Auth repository
 */
interface AuthRepository {

    /**
     * Register user
     *
     * @param params fullName, password, email, avatar
     * @return
     */
    suspend fun registerUser(params: RegisterUser): BaseResponse<Any>?

    /**
     * Login user
     *
     * @param params: email and password
     * @return
     */
    suspend fun loginUser(params: LoginUser): BaseResponse<Any>?

    /**
     * Reset user password
     *
     * @param params: email and password
     * @return
     */
    suspend fun resetUserPassword(params: ResetPassword): BaseResponse<Any>?

}