package com.grandstream.multiplatform.di

import app.cash.sqldelight.db.SqlDriver
import com.grandstream.multiplatform.database.ContactDatabase

object DatabaseWrapper {
    lateinit var instance: ContactDatabase
    fun init(sqlDriver: SqlDriver) {
        instance = ContactDatabase(sqlDriver)
    }
}