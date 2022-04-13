package com.peacemaker.BaseResponse

import io.ktor.http.*

sealed class BaseResponse<T>(
    val statusCode: HttpStatusCode = HttpStatusCode.OK
){
    data class SuccessResponse<T>(
        val data: T? = null,
        val message: String?= null
    ): BaseResponse<T>()

    data class ErrorResponse<T>(
        val message: String? = null,
        val exception: T? = null
    ):BaseResponse<T>()

}
