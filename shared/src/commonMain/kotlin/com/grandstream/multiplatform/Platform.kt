package com.grandstream.multiplatform

import app.cash.sqldelight.db.SqlDriver
import io.ktor.client.*
import kotlinx.coroutines.MainScope
interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient

//expect fun createDriver(): SqlDriver
expect fun tag(): String