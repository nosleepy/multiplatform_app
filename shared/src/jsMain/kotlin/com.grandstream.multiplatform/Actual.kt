package com.grandstream.multiplatform

import io.ktor.client.engine.js.Js
import org.koin.dsl.module

actual fun platformModule() = module {
    single { Js.create() }
}