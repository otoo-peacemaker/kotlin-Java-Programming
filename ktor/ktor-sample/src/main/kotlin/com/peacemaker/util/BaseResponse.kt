package com.peacemaker.util

import io.ktor.http.*

/**
 * @author Peacemaker Otoo
 * Request response handler class
 * */
sealed class BaseResponse<T>(
    open val statusCode: HttpStatusCode
) {
    data class SuccessResponse<T>(
        val data: T? = null,
        val message: String? = null,
        override val statusCode: HttpStatusCode
    ) : BaseResponse<T>(statusCode)

    data class ErrorResponse<T>(
        val message: String? = null,
        val exception: T? = null, override
        val statusCode: HttpStatusCode
    ) : BaseResponse<T>(statusCode)

}
