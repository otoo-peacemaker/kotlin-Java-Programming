package com.peacemaker

import com.peacemaker.db.DatabaseFactory
import com.peacemaker.routes.configureRouting
import com.peacemaker.plugins.configureSecurity
import io.ktor.server.application.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    /*install(CORS) {
        anyHost()
    }
    install(ContentNegotiation){
        json()
    }//media type specification, accepting json*/

    DatabaseFactory.init()
    configureRouting()
    configureSecurity()
}
