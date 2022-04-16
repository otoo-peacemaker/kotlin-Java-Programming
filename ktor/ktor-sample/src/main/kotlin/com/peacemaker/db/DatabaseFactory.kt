package com.peacemaker.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    /**@author Peacemaker Otoo
     * Factory for the database
     *
     * @param [dbUserName]: Database user name
     * @param [databaseName]: Database name
     * @param [password] : Database password
     * */

    private const val dbUserName = "postgres"
    private const val databaseName = "database"
    private const val password = "123456"

    /**
     * DatabaseFactory initializer method
     * */
    fun init(){
        //initialize the connection
        Database.connect(hikari())

        //create schema
        transaction {
            SchemaUtils.create(UserTable)
        }

    }


    /**
     * Database config
     * @return [HikariConfig] Return the database configuration
     * */
    private fun hikari(): HikariDataSource{
        val config = HikariConfig()
        config.driverClassName = "org.postgresql.Driver"
        config.jdbcUrl = "jdbc:postgresql:$databaseName?user=$dbUserName"
        config.maximumPoolSize = 3
        config.password = password
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()
        return HikariDataSource(config)
    }

    /**
     * Transactions are executed synchronously on the current thread,
     * so they will block other parts of your application!
     * If you need to execute a transaction asynchronously, consider running it on a separate Thread.
     *
     * Below is a generic function to perform the said [note] queries to the database
     * @param [block]: A lambda function for transaction statement for the query anywhere in our db
     * */

    suspend fun<T> dbQuery(block: () -> T):T = withContext(Dispatchers.IO){
        transaction {
            block()
        }
    }

    suspend fun<T> dbIntQuery(block: () -> Int):Int = withContext(Dispatchers.IO){
        transaction {
            block()
        }
    }

}