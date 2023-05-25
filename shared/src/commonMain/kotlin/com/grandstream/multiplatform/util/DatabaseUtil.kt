package com.grandstream.multiplatform.util

import app.cash.sqldelight.db.SqlDriver
import com.grandstream.multiplatform.database.ContactDatabase
import comgrandstreammultiplatformdatabase.ContactDatabaseQueries

object DatabaseUtil {
    lateinit var dbQuery: ContactDatabaseQueries
    fun init(sqlDriver: SqlDriver) {
        dbQuery = ContactDatabase(sqlDriver).contactDatabaseQueries
    }
}