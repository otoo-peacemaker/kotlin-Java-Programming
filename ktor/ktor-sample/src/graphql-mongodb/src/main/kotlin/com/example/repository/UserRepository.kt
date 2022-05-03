package com.example.repository

import com.example.model.User
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

class UserRepository(client: MongoClient) : Repository<User> {
    override lateinit var mongoCollection: MongoCollection<User>

    init {
        val database = client.getDatabase("databaseName")
        mongoCollection = database.getCollection<User>("User")
    }

    fun getUserByEmail(email: String? = null): User? {
        return try {
            mongoCollection.findOne(User::email eq email)
        } catch (t: Throwable) {
            throw Exception("Cannot get user with that email")
        }
    }
}
