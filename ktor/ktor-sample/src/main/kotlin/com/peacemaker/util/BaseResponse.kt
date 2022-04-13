package com.peacemaker.util

import io.ktor.http.*

/**
 * @author Peacemaker Otoo
 * Request response handler class
 * */
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
    ): BaseResponse<T>()

}
