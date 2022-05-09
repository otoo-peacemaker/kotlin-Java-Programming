package com.example.services

import com.example.model.User
import com.example.model.response.UserInput
import com.example.model.response.UserResponse
import com.example.repository.UserRepository
import com.mongodb.client.MongoClient
import com.sun.org.apache.xml.internal.security.algorithms.Algorithm
import io.ktor.server.application.*
import org.koin.core.component.KoinComponent
import java.nio.charset.StandardCharsets
import java.util.*

class AuthService : KoinComponent {
    private val client: MongoClient by inject()
    private val repository: UserRepository = UserRepository(client)
    private val secret: String = "someHashedValueNobodyIsAbleToGuess"
    private val algorithm: Algorithm = Algorithm.HMAC256(secret)
    private val verifier: JWTVerifier = JWT.require(algorithm).build()

    fun signIn(userInput: UserInput): UserResponse? {
        val user = repository.getUserByEmail(userInput.email) ?: error("No such user by that email")
        if (BCrypt.verifyer()
                .verify(userInput.password.toByteArray(StandardCharsets.UTF_8), user.hashedPassword).verified
        ) {
            val token = signAccessToken(user.id)
            return UserResponse(token, user)
        }
        error("Password is incorrect")
    }

    fun signUp(userInput: UserInput): UserResponse? {
        val hashedPassword = BCrypt.withDefaults().hash(10, userInput.password.toByteArray(StandardCharsets.UTF_8))
        val id = UUID.randomUUID().toString()
        val emailUser = repository.getUserByEmail(userInput.email)

        if (emailUser != null) {
            error("Email already in use")
        }
        val newUser = repository.add(
            User(
                id = id,
                email = userInput.email,
                hashedPassword = hashedPassword
            )
        )

        val token = signAccessToken(newUser.id)
        return UserResponse(token, newUser)
    }

    fun verifyToken(call: ApplicationCall): User? {
        return try {
            val authHeader = call.request.headers["Authorization"] ?: ""
            val token = authHeader.split("Bearer ").last()
            val accessToken = verifier.verify(JWT.decode(token))
            val userId = accessToken.getClaim("userId").asString()
            return User(id = userId, email = "", hashedPassword = byteArrayOf())
        } catch (e: Exception) {
            print(e.message)
            null
        }
    }

    private fun signAccessToken(id: String): String {
        return JWT.create().withIssuer("example")
            .withClaim("userId", id)
            .sign(algorithm)
    }
}
