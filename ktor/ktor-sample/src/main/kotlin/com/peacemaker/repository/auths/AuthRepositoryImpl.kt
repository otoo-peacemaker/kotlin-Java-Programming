package com.peacemaker.repository.auths

import com.peacemaker.security.JWTConfig
import com.peacemaker.models.LoginUser
import com.peacemaker.models.RegisterUser
import com.peacemaker.models.ResetPassword
import com.peacemaker.service.auth.AuthService
import com.peacemaker.util.BaseResponse
import io.ktor.http.*

/**
 * Auth repository impl
 *
 * @property userService
 * @constructor Create empty Auth repository impl
 */
class AuthRepositoryImpl(private val userService: AuthService) : AuthRepository {

    /**Check user by email, if exist throw[exception] else register new user
     * */
    override suspend fun registerUser(params: RegisterUser): BaseResponse<Any> {

        return if (isEmailExist(params.email)) {
            BaseResponse.ErrorResponse(
                message = "Email already Exist",
                HttpStatusCode.Conflict
            )

        } else {
            val user = userService.registerUser(params)
            if (user != null) {
                val token = JWTConfig.instance.createAccessToken(user.id)
                user.authToken = token
                BaseResponse.SuccessResponse(data = user, "Registration successfully", HttpStatusCode.OK)
            } else {
                BaseResponse.ErrorResponse(statusCode = HttpStatusCode.BadRequest)
            }
        }
    }

    override suspend fun loginUser(params: LoginUser): BaseResponse<Any> {
        return when {
            !isEmailExist(params.email) -> {
                BaseResponse.ErrorResponse(
                    message = "Email does not exist",
                    HttpStatusCode.Unauthorized
                )

            }
            !isPasswordCorrect(params.email,params.password) -> {
                BaseResponse.ErrorResponse(
                    message = "Incorrect password",
                    HttpStatusCode.Unauthorized
                )
            }

            else -> {
                val user = userService.loginUser(params.email, params.password)
                if (user != null) {
                    val token = JWTConfig.instance.createAccessToken(user.id)
                    user.authToken = token
                    BaseResponse.SuccessResponse(data = user, "Login successful", HttpStatusCode.OK)
                } else {
                    BaseResponse.ErrorResponse(statusCode = HttpStatusCode.NotFound)
                }
            }
        }
    }

    override suspend fun resetUserPassword(params: ResetPassword): BaseResponse<Any> {
        return when {
            !isEmailExist(params.email) -> {
                BaseResponse.ErrorResponse(
                    message = "Account does not exist",
                    HttpStatusCode.Unauthorized
                )
            }

            else -> {
                val reset = userService.resetPassword(params.email,params.password)
                if (reset != null) {
                    BaseResponse.SuccessResponse(data = reset, "success", HttpStatusCode.OK)
                } else {
                    BaseResponse.ErrorResponse(
                        message = "Reset password failed",
                        HttpStatusCode.Unauthorized
                    )
                }
            }
        }
    }

    /**checking if email exist or not*/
    private suspend fun isEmailExist(email: String): Boolean {
        return userService.findUserByEmail(email) != null
    }

    /**checking if password exist*/
    private suspend fun isPasswordCorrect(email: String, password: String): Boolean {
        return userService.ifPasswordCorrect(email, password) != null
    }
}