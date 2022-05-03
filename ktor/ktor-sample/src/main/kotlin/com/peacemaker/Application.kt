package com.peacemaker

import com.peacemaker.db.DatabaseFactory
import com.peacemaker.repository.UserRepository
import com.peacemaker.repository.UserRepositoryImpl
import com.peacemaker.routes.authRoutes
import com.peacemaker.security.configureSecurity
import com.peacemaker.service.UserService
import com.peacemaker.service.UserServiceImpl
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {


    DatabaseFactory.init()
    install(ContentNegotiation) {
        jackson()
    }
    configureSecurity()

    routing {
        get("/"){
            call.respondText("Hello, Buddy", contentType = ContentType.Text.Plain)
        }//Get this text when the base url is searched
    }

    val service:UserService = UserServiceImpl()
    val repository: UserRepository =UserRepositoryImpl(service)
    authRoutes(repository)

    /*install(CORS) {
        anyHost()
    }*/
}
