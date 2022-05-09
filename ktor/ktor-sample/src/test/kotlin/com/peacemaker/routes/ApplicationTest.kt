package com.peacemaker.routes

import com.peacemaker.db.DatabaseFactory
import com.peacemaker.models.LoginUser
import com.peacemaker.models.RegisterUser
import com.peacemaker.repository.auths.AuthRepository
import com.peacemaker.repository.auths.AuthRepositoryImpl
import com.peacemaker.security.configureInstallation
import com.peacemaker.security.configureRouting
import com.peacemaker.security.configureSecurity
import com.peacemaker.service.auth.AuthService
import com.peacemaker.service.auth.AuthServiceImpl
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.jackson.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

open class ApplicationTest {
    private val service: AuthService = AuthServiceImpl()
    private val repository: AuthRepository = AuthRepositoryImpl(service)

    @Test
    fun testRoot() = testApplication {
        application {
            configureRouting()
            authRoutes(repository)
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello, Welcome to my Backend Services", bodyAsText())
        }
    }


    @Test
    fun testPostLogin() = testApplication {
        application {
            configureRouting()
            authRoutes(repository)
        }

        val client = createClient {
            this@testApplication.install(ContentNegotiation) {
                jackson()
            }
        }
        val response = client.post("/auth/login") {
            header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            //  setBody(listOf("email" to "nanak@gmail.com", "password" to "12345678"))
            setBody(repository.loginUser(LoginUser("123456", "nanak@gmail.com")))
        }

        assertEquals("Login successfully", response.bodyAsText())
        assertEquals(HttpStatusCode.Created, response.status)
    }


    @Test
    fun testPostRegistration() = testApplication {
        DatabaseFactory.init()
        application {
            configureSecurity()
            configureInstallation()
            configureRouting()
            authRoutes(repository)
        }

        val client = createClient {
            this@testApplication.install(ContentNegotiation) {
                jackson()
            }
        }

        val response = client.post("/auth/register") {
            header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded.toString())
            setBody(
                repository.registerUser(
                    RegisterUser(
                        "Peacemaker",
                        "12345678",
                        "nanakwesiotoo@gmaillllllll.com",
                        "message"
                    )
                )
            )
        }

        assertEquals("Registered successfully", response.bodyAsText())
        assertEquals(HttpStatusCode.OK, response.status)
    }
}