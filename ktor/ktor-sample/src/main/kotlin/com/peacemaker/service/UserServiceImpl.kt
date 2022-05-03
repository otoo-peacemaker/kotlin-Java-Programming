package com.peacemaker.service

import com.peacemaker.db.DatabaseFactory.dbQuery
import com.peacemaker.db.UserTable
import com.peacemaker.models.LoginUser
import com.peacemaker.models.RegisterUser
import com.peacemaker.models.ResetPassword
import com.peacemaker.models.User
import com.peacemaker.security.encryptPassword
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement

class UserServiceImpl : UserService {
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
        return rowToUser(statement?.resultedValues?.get(0))
    }


    /**A function to return signle or null if user already exist in the database
     * or not
     * @param [email]: For validation
     * @return query email from the database
     * */
    override suspend fun findUserByEmail(email: String): User? {
        return dbQuery {
            UserTable.select { UserTable.email.eq(email) }
                .map { rowToUser(it) }.singleOrNull()
        }
    }

    /**check if password exist*/
    override suspend fun ifPasswordCorrect(email: String, password: String): User? {
        return dbQuery {
            UserTable.select {
                UserTable.password.eq(password) and (UserTable.email eq (email))
            }
                .map { rowToUser(it) }.singleOrNull()
        }
    }

    override suspend fun loginUser(params: LoginUser): User? {
        return dbQuery {
            /*    val query = UserTable.selectAll()
             val qu =  with(UserTable){
                    email?.let {
                        query.andWhere {
                            password eq it
                        }
                    }
                }*/
            UserTable.select {
                /* with(UserTable){
                     email?.let {
                         query.andWhere {
                             password eq it
                         }
                     }
                 }*/
                UserTable.email eq (params.email) and (UserTable.password eq (params.password))
            }
                .map {
                    rowToUser(it)
                }.singleOrNull()
        }
    }

    /**
     * We can now use what we have learned about queries and insertions
     * to update existing data in the database.
     * Indeed,a simple update looks like a combination of a select with an insert:
     * */
    override suspend fun resetPassword(params: ResetPassword): User? {
      /*  return dbQuery {

            UserTable.update({
                UserTable.email eq params.email }) {
                it[password] = params.password
            }
        }*/

       return  null
    }


    /**A Function to insert user in a row to the database*/
    private fun rowToUser(row: ResultRow?): User? {
        return if (row == null) null
        else User(
            id = row[UserTable.id],
            fullName = row[UserTable.fullName],
            email = row[UserTable.email],
            avatar = row[UserTable.avatar],
            createdAt = row[UserTable.createdAt].toString()
        )
    }
}