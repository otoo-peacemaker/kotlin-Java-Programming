package com.peacemaker.security

import io.ktor.server.auth.*

/**Authenticate the user*/

data class UserIdPrincipal(val id:Int): Principal