package com.welbtek

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.welbtek.plugins.*

fun main() {
    embeddedServer(Netty, port = 13853, host = "127.0.0.1") {
        configureRouting()
    }.start(wait = true)
}
