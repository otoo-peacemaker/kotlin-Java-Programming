package com.peacemaker

import com.peacemaker.db.DatabaseFactory
import com.peacemaker.routes.baseRoute
import com.peacemaker.security.configureInstallation
import com.peacemaker.security.configureRouting
import com.peacemaker.security.configureSecurity
import io.ktor.server.application.*


fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    configureSecurity()
    configureInstallation()
    baseRoute()
    configureRouting()

    DatabaseFactory.init()
}
