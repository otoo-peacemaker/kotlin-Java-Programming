package com.peacemaker.routes

import com.peacemaker.repository.auths.AuthRepository
import com.peacemaker.models.LoginUser
import com.peacemaker.models.RegisterUser
import com.peacemaker.models.ResetPassword
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


/**
 * Auth routes
 *
 * @param repository
 */
fun Application.authRoutes(repository: AuthRepository) {
    routing {
        authenticate {
            get("/test") {
                call.respond("testing token")
            }
        }

        route("/auth") {
            post("/register") {
                val params = call.receive<RegisterUser>()
                val result = repository.registerUser(params)
                if (result != null) {
                    result.statusCode?.let { it1 -> call.respond(it1, result) }
                }
            }

            post("/login") {
                val params = call.receive<LoginUser>()
                val result = repository.loginUser(params)
                if (result != null) {
                    result.statusCode?.let { it1 -> call.respond(it1, result) }
                }
            }

            post("/resetPassword") {
                val params = call.receive<ResetPassword>()
                val result = repository.resetUserPassword(params)
                if (result != null) {
                    result.statusCode?.let { it1 -> call.respond(it1, result) }
                }
            }
        }
    }
}