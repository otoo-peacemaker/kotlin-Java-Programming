package com.peacemaker.routes

import com.peacemaker.repository.UserRepository
import com.peacemaker.service.RegisterUser
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.authRoutes(repository: UserRepository){
    routing {
        route("/auth"){
            post("/register"){
                val params = call.receive<RegisterUser>()
                val result = repository.registerUser(params)
                if (result != null) {
                    call.respond(result.statusCode,result)
                }
            }
        }
    }
}