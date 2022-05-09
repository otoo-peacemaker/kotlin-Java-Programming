package com.peacemaker.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.baseRoute(){
    routing {
        get("/") {
            call.respondText("Hello, Welcome to my Backend Services", contentType = ContentType.Text.Plain)
        }//Get this text when the base url is searched
    }
}