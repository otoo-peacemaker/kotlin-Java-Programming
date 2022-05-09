package com.peacemaker

import com.peacemaker.repository.auths.AuthRepository
import com.peacemaker.repository.auths.AuthRepositoryImpl
import com.peacemaker.routes.authRoutes
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.peacemaker.service.auth.AuthService
import com.peacemaker.service.auth.AuthServiceImpl

class ApplicationTest {
    private val service: AuthService = AuthServiceImpl()
    private val repository: AuthRepository = AuthRepositoryImpl(service)

    @Test
    fun testRoot() = testApplication {
        application {
            //configureRouting()
            authRoutes(repository)
        }
        client.get("/register").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("", bodyAsText())
        }
    }

    @org.junit.Test
    fun testAddCustomer() {
        withTestApplication({ module(testing = true) }) {
            handleRequest(HttpMethod.Post, "/customer").apply {
                assertEquals(
                    response.content,
                    "{\"id\": \"100\",\"firstName\": \"Jane\",\"lastName\": \"Smith\",\"email\": \"jane.smith@company.com\"}"
                )
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}