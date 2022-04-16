package com.peacemaker.repository

import com.peacemaker.security.JWTConfig
import com.peacemaker.service.LoginUser
import com.peacemaker.service.RegisterUser
import com.peacemaker.service.ResetPassword
import com.peacemaker.service.UserService
import com.peacemaker.util.BaseResponse

class UserRepositoryImpl(private val userService: UserService) : UserRepository {

    /**Check user by email, if exist throw[exception] else register new user
     * */
    override suspend fun registerUser(params: RegisterUser): BaseResponse<Any>? {

        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = "Email already Exist")
        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                val token = JWTConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, "Registration successful")
            } else {
                BaseResponse.ErrorResponse()
            }
        }
    }

    override suspend fun loginUser(params: LoginUser): BaseResponse<Any>? {
        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(message = "Account does not exist", "Registered email mismatch")
        } else if (isPasswordCorrect(params.password)) {
            BaseResponse.ErrorResponse(message = "Incorrect password", "password mismatch")
        } else {
            val user = userService.loginUser(params)
            if (user != null) {
                val token = JWTConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, "")
            } else {
                BaseResponse.ErrorResponse()
            }
        }


        /* when {
             !isEmailExist(params.email) -> {
                 BaseResponse.ErrorResponse(message = "Account does not exist", "Registered email mismatch")
             }
             !isPasswordCorrect(params.password) -> {
                 BaseResponse.ErrorResponse(message = "Incorrect password", "password mismatch")
             }
             else -> {
                 val user = userService.loginUser(params)
                 if (user != null) {
                     val token = JWTConfig.instance.createAccessToken(user.id)
                     user.authToken = token
                     BaseResponse.SuccessResponse(data = user, "")
                 } else {
                     BaseResponse.ErrorResponse()
                 }
             }
         }*/
    }

    override suspend fun resetUserPassword(params: ResetPassword): BaseResponse<Any>? {
        return when {
            !isEmailExist(params.email) -> {
                BaseResponse.ErrorResponse(message = "Account does not exist", "Email not registered")
            }
            !isPasswordCorrect(params.password) -> {
                BaseResponse.ErrorResponse(message = "Incorrect password", "password mismatch")
            }
            else -> {
                val reset = userService.resetPassword(params)
                if (reset != null) {
                    BaseResponse.SuccessResponse(data = reset, "")
                } else {
                    BaseResponse.ErrorResponse()
                }
            }
        }
    }

    /**checking if email exist or not*/
    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }

    /**checking if password exist*/
    private suspend fun isPasswordCorrect(password: String): Boolean {
        return userService.ifPasswordCorrect(password) != null
    }
}