package com.peacemaker.repository

import com.peacemaker.BaseResponse.BaseResponse
import com.peacemaker.service.RegisterUser
import com.peacemaker.service.UserService

class UserRepositoryImpl(private val userService: UserService) : UserRepository {

    /**Check user by email, if exist throw[exception] else register new user
     * */
    override suspend fun registerUser(params: RegisterUser): BaseResponse<Any>? {

        return if(isEmailExist(params.email)){
            BaseResponse.ErrorResponse(message = "Email already Exist")
        }else{
            val user  = userService.registerUser(params)
            if (user!=null){
                //Todo generate auth token
                BaseResponse.SuccessResponse(data = user)
            }else{
                BaseResponse.ErrorResponse()
            }
        }
    }

    override suspend fun loginUser(email: String, password: String): BaseResponse<Any>? {
        TODO("Not yet implemented")
    }

    /**checking if email exist or not*/
    private suspend fun isEmailExist(email: String):Boolean {
        return userService.findUserByEmail(email) !=null
    }
}