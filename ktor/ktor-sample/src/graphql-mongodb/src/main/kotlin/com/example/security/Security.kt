package com.example.security

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
