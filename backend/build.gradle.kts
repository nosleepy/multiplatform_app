plugins {
    application
    kotlin("jvm")
    kotlin("plugin.serialization")
//    id("io.ktor.plugin")
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    with(Deps.Ktor) {
        implementation(serverCors)
        implementation(serverCore)
        implementation(serverNetty)
        implementation(serverNegotiation)
        implementation(json)
    }
    implementation("ch.qos.logback:logback-classic:1.2.11")
}