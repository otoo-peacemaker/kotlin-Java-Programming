package com.example.model

data class User(override val id: String, val email: String, val hashedPassword: ByteArray) : Model {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (email != other.email) return false
        if (!hashedPassword.contentEquals(other.hashedPassword)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + email.hashCode()
        result = 31 * result + hashedPassword.contentHashCode()
        return result
    }
}
