package com.peacemaker

import com.peacemaker.repository.UserRepository
import com.peacemaker.repository.UserRepositoryImpl
import com.peacemaker.routes.authRoutes
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import com.peacemaker.service.UserService
import com.peacemaker.service.UserServiceImpl

class ApplicationTest {
    private val service: UserService = UserServiceImpl()
    private val repository: UserRepository = UserRepositoryImpl(service)

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