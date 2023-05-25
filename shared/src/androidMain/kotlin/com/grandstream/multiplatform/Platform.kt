package com.grandstream.multiplatform

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.blankj.utilcode.util.ActivityUtils
import com.grandstream.multiplatform.database.ContactDatabase
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Android) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}

fun createDriver(): SqlDriver {
    return AndroidSqliteDriver(ContactDatabase.Schema, ActivityUtils.getTopActivity(), "contact.db")
}

actual fun tag(): String = "AndroidApp"