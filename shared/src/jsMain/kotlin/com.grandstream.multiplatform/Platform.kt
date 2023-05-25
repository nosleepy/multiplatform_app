package com.grandstream.multiplatform

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.sqljs.initSqlDriver
import com.grandstream.multiplatform.database.ContactDatabase
import io.ktor.client.*
import io.ktor.client.engine.js.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import kotlin.js.Promise

class JsPlatform : Platform {
    override val name: String = "Js"
}

actual fun getPlatform(): Platform = JsPlatform()

actual fun httpClient(config: HttpClientConfig<*>.() -> Unit) = HttpClient(Js) {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        })
    }
}

fun createDriverPromise(): Promise<SqlDriver> {
    return initSqlDriver(ContactDatabase.Schema)
}

actual fun tag(): String = "JsApp"