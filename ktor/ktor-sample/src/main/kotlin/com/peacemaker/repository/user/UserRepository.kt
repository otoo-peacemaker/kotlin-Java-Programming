package com.peacemaker.repository.user

import com.peacemaker.util.BaseResponse

interface UserRepository {

    suspend fun getUser(id: Int): BaseResponse<Any>
}