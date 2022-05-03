package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*

fun main() {
    embeddedServer(Netty, port = 80, host = "172.0.0.1") {
        configureRouting()
        configureSecurity()
        configureHTTP()
        configureTemplating()
        configureSerialization()
    }.start(wait = true)
}
