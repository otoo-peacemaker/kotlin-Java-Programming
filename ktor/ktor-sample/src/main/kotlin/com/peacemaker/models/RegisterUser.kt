package com.peacemaker.models

data class RegisterUser(
    val fullName: String,
    val password: String,
    val email: String,
    val avatar: String,
)
