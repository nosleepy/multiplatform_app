object Versions {
    const val ktor = "2.2.4"
    const val sqlDelight = "2.0.0-alpha05"
    const val kotlinx = "1.6.4"
    const val koin = "3.4.0"
    const val preCompose = "1.4.1"
    const val napier = "2.6.1"
}

object Deps {
    object Ktor {
        const val clientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val clientNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
        const val clientLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val json = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
        const val clientDarwin = "io.ktor:ktor-client-darwin:${Versions.ktor}"
        const val clientAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val clientJs = "io.ktor:ktor-client-js:${Versions.ktor}"
        const val clientJava = "io.ktor:ktor-client-java:${Versions.ktor}"
        const val serverCors = "io.ktor:ktor-server-cors:${Versions.ktor}"
        const val serverCore = "io.ktor:ktor-server-core:${Versions.ktor}"
        const val serverNetty = "io.ktor:ktor-server-netty:${Versions.ktor}"
        const val serverNegotiation = "io.ktor:ktor-server-content-negotiation:${Versions.ktor}"
    }

    object SqlDelight {
        const val runtime = "app.cash.sqldelight:runtime:${Versions.sqlDelight}"
        const val coroutinesExtensions = "app.cash.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
        const val androidDriver = "app.cash.sqldelight:android-driver:${Versions.sqlDelight}"
        const val jsDriver = "app.cash.sqldelight:sqljs-driver:${Versions.sqlDelight}"
        const val javaDriver = "app.cash.sqldelight:sqlite-driver:${Versions.sqlDelight}"
    }

    object Kotlinx {
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinx}"
    }

    object Koin {
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
    }

    object PreCompose {
        const val core = "moe.tlaster:precompose:${Versions.preCompose}"
        const val molecule = "moe.tlaster:precompose-molecule:${Versions.preCompose}"
        const val viewmodel = "moe.tlaster:precompose-viewmodel:${Versions.preCompose}"
    }

    object Napier {
        const val core = "io.github.aakira:napier:${Versions.napier}"
    }

    object Androidx {
        const val activityCompose = "androidx.activity:activity-compose:1.6.1"
        const val appcompat = "androidx.appcompat:appcompat:1.6.1"
        const val coreKtx = "androidx.core:core-ktx:1.9.0"
        const val preview = "androidx.compose.ui:ui-tooling-preview:1.2.1"
    }
}