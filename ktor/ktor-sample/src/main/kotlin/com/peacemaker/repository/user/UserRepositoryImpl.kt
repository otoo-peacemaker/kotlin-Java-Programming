package com.peacemaker.repository.user

import com.peacemaker.util.BaseResponse
import com.peacemaker.service.user.UserService

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUser(id: Int): BaseResponse<Any> {
        return BaseResponse.SuccessResponse(data = userService.getUser(id),null,null)
    }
}