package com.peacemaker.security

import io.ktor.server.auth.*

/**Authenticate the user*/

data class UserIdPrincipalForUser(val id:Int): Principal