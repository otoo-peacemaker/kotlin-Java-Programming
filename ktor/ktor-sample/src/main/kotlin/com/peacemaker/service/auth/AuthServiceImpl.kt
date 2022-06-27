package com.peacemaker.service.auth

import com.peacemaker.db.DatabaseFactory.dbQuery
import com.peacemaker.db.extensions.toUser
import com.peacemaker.db.schema.UserTable
import com.peacemaker.models.LoginUser
import com.peacemaker.models.RegisterUser
import com.peacemaker.models.ResetPassword
import com.peacemaker.models.User
import com.peacemaker.security.encryptPassword
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement
import javax.management.Query.and

class AuthServiceImpl : AuthService {
    /**
     * @author Peacemaker Otoo
     * A class to implement user services to perform DML database transaction queries
     * */

    private var statement: InsertStatement<Number>? = null
    /**Query statement to register user to the database*/
    override suspend fun registerUser(params: RegisterUser): User? {
        dbQuery {
            statement = UserTable.insert {
                it[fullName] = params.fullName
                it[password] = encryptPassword(params.password)
                it[email] = params.email
                it[avatar] = params.avatar
            }
        }
        return statement?.resultedValues?.get(0).toUser()
    }

    override suspend fun loginUser(email: String, password: String): User? {
        val hashedPassword = encryptPassword(password)
        val userRow = dbQuery {
            UserTable.select {
                UserTable.email eq email and (UserTable.password eq hashedPassword)
            }.firstOrNull()
        }
        return userRow.toUser()
    }


    override suspend fun resetPassword(email: String, password: String): User? {
        val hashedPassword = encryptPassword(password)
        dbQuery {
            UserTable.update({ UserTable.email eq email }) {
                it[UserTable.password] = hashedPassword
            }
        }
        return statement?.resultedValues?.get(0).toUser()
    }


    /**A function to return signle or null if user already exist in the database
     * or not
     * @param [email]: For validation
     * @return query email from the database
     * */
    override suspend fun findUserByEmail(email: String): User? {
        return dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { it.toUser() }.singleOrNull()
        }
    }


    override suspend fun ifPasswordCorrect(email: String, password: String): User? {
        val hashedPassword = encryptPassword(password)
        return dbQuery {
            UserTable.select {
                UserTable.email eq email and (UserTable.password eq (hashedPassword))
            }.map { it.toUser() }.singleOrNull()
        }
    }
}

