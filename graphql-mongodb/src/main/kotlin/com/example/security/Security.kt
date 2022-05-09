package com.example.security

import com.example.model.User
import com.example.model.response.UserInput
import com.example.services.AuthService

fun SchemaBuilder.authSchema(authService: AuthService) {

    type<User> {
        description = "User details"
        User::hashedPassword.ignore()
    }

    inputType<UserInput> {
        description = "User credentials"
    }

    mutation("signIn") {
        description = "Authenticate an existing user"

        resolver { userInput: UserInput ->
            try {
                authService.signIn(userInput)
            } catch (e: Exception) {
                null
            }
        }
    }

    mutation("signUp") {
        description = "Authenticate a new user"

        resolver { userInput: UserInput ->
            try {
                authService.signUp(userInput)
            } catch (e: Exception) {
                null
            }
        }
    }
}
