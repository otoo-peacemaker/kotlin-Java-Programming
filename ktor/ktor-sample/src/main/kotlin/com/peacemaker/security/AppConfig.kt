package com.peacemaker.security


import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import com.peacemaker.di.RepositoryProvider
import com.peacemaker.routes.authRoutes
import com.peacemaker.routes.userRoutes
import com.peacemaker.util.BaseResponse
import com.peacemaker.util.INVALID_AUTHENTICATION_TOKEN
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureSecurity() {
    JWTConfig.initializeJwt("kwesi-welbred")
    install(Authentication) {
        jwt {
            verifier(JWTConfig.instance.verifier)
            validate {
                val claim = it.payload.getClaim(JWTConfig.CLAIM).asInt()
                if (claim != null) {
                    UserIdPrincipalForUser(claim)
                } else {
                    null
                }
            }
            //return this if jwt auth fails
            challenge { defaultScheme, realm ->
                call.respond(
                    /*    UnauthorizedResponse(
                            HttpAuthHeader.Parameterized(
                                defaultScheme,
                                mapOf(HttpAuthHeader.Parameters.Realm to realm)
                            )
                        )
                    )*/
                    BaseResponse.ErrorResponse(INVALID_AUTHENTICATION_TOKEN, statusCode = HttpStatusCode.BadRequest)
                )
            }
        }
    }
}

fun Application.configureRouting() {
    authRoutes(RepositoryProvider().provideAuthRepository())
    userRoutes(RepositoryProvider().provideUserRepository())
}

fun Application.configureInstallation() {
    install(ContentNegotiation) {
        jackson()
    }

    //allow cross-origin-request
    install(CORS) {
        anyHost()
    }


    //allows Ktor applications to respond appropriately to any failure state based on a thrown exception or status code.
    install(StatusPages) {
        exception<MismatchedInputException> { call, cause ->
            val error = when (cause) {
                is MissingKotlinParameterException -> BaseResponse.ErrorResponse(
                    "Missing attribute `${cause.parameter.name}`",
                    statusCode = HttpStatusCode.BadRequest
                )
                else -> BaseResponse.ErrorResponse(cause.message!!, statusCode = HttpStatusCode.BadRequest)
            }
            error.statusCode?.let { call.respond(it, error) }
        }
        exception<JsonParseException> { call, cause ->
            call.respond(BaseResponse.ErrorResponse(cause.message!!, statusCode = HttpStatusCode.BadRequest))
        }


        exception<Throwable> { call, cause ->
            if (cause is BaseResponse.AuthorizationException) {
                call.respondText(text = "403: ${cause.message}", status = HttpStatusCode.Forbidden)
            } else {
                call.respondText(text = "500: ${cause.message}", status = HttpStatusCode.InternalServerError)
            }
        }

        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(text = "404: Page Not Found", status = status)
        }

        statusFile(HttpStatusCode.Unauthorized, HttpStatusCode.PaymentRequired, filePattern = "error#.html")
    }

}


