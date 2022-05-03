package com.peacemaker.routes

import com.peacemaker.repository.UserRepository
import com.peacemaker.models.LoginUser
import com.peacemaker.models.RegisterUser
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.authRoutes(repository: UserRepository) {
    routing {
        authenticate {
            get("/testurl") {
                call.respond("testing token")
            }
        }

        route("/auth") {
            post("/register") {
                val params = call.receive<RegisterUser>()
                val result = repository.registerUser(params)
                if (result != null) {
                    call.respond(result.statusCode, result)
                }
            }
        }


        route("/auth") {
            post("/login") {
                val params = call.receive<LoginUser>()
                val result = repository.loginUser(params)
                if (result != null) {
                    call.respond(result.statusCode, result)
                }
            }
        }
    }
}