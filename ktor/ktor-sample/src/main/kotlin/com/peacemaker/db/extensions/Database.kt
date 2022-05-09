package com.peacemaker.db.extensions

import com.peacemaker.db.schema.UserTable
import com.peacemaker.models.User
import org.jetbrains.exposed.sql.ResultRow


/**A Function to insert user in a row to the database*/
fun ResultRow?.toUser(): User? {
    return if (this == null) null
    else User(
        id = this[UserTable.id],
        fullName = this[UserTable.fullName],
        avatar = this[UserTable.avatar],
        email = this[UserTable.email],
        createdAt = this[UserTable.createdAt].toString()
    )
}
