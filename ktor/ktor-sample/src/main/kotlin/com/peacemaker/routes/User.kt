package com.peacemaker.routes

import com.peacemaker.repository.user.UserRepository
import com.peacemaker.security.UserIdPrincipalForUser
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlin.text.get

fun Application.userRoutes(repository: UserRepository) {
    routing {
        authenticate {
            route("/user") {
                get {
                    val principal = call.principal<UserIdPrincipalForUser>()
                    val result = repository.getUser(principal?.id!!)
                    result.statusCode?.let { it1 -> call.respond(it1, result) }
                }
            }
        }
    }
}