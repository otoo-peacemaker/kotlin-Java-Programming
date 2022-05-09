package com.peacemaker.util

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import io.ktor.http.*

/**
 * @author Peacemaker Otoo
 * Request response handler class
 * */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize
sealed class BaseResponse<T>(
    open val statusCode: HttpStatusCode?
) {
    @JsonSerialize
    data class SuccessResponse<T>(
        val data: T? = null,
        val message: String? = null,
        override val statusCode: HttpStatusCode?
    ) : BaseResponse<T>(statusCode)

    @JsonSerialize
    data class ErrorResponse(
        val message: String? = null,
        override val statusCode: HttpStatusCode?
    ) : BaseResponse<Any>(statusCode)

    @JsonSerialize
    open class AuthorizationException(override val message: String?) : Throwable()
}
