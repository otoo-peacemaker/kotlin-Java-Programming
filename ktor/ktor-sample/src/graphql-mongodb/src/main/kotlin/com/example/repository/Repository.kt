package com.example.repository

import com.example.model.Model
import com.mongodb.client.MongoCollection
import org.litote.kmongo.eq
import org.litote.kmongo.findOne

interface Repository<T> {
    var mongoCollection: MongoCollection<T>

    fun getById(id: String): T {
        return try {
            mongoCollection.findOne(Model::id eq id) ?: throw Exception("No item with that ID exists")
        } catch (t: Throwable) {
            throw PropertyNotFoundException("Cannot find item")
        }
    }

    fun getAll(): List<T> {
        return try {
            val result = mongoCollection.find()
            result.asIterable().map { it }
        } catch (t: Throwable) {
            throw Exception("Cannot get all items")
        }
    }

    fun delete(id: String): Boolean {
        return try {
            mongoCollection.findOneAndDelete(Model::id eq id) ?: Exception("No item with that Id exists")
            true
        } catch (t: Throwable) {
            throw Exception("Cannot delete item or item not found")
        }
    }

    fun add(entry: T): T {
        return try {
            mongoCollection.insertOne(entry)
            entry
        } catch (t: Throwable) {
            throw Exception("Cannot add item")
        }
    }

    fun update(entry: Model): T{
        return try {
            mongoCollection.updateOne(Model::id eq entry.id, entry)
            mongoCollection.findOne(Model::id eq entry.id) ?: throw PropertyNotFoundException("No item with that id exists")
        }catch (t: Throwable){
            throw Exception("Cannot update item")
        }
    }
}
