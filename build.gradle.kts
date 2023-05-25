buildscript {
    dependencies {
//        classpath("com.android.tools.build:gradle:7.2.0")
    }
}
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.2.0").apply(false)
    id("com.android.library").version("7.2.0").apply(false)
    id("org.jetbrains.compose").version("1.4.0").apply(false)
    id("io.ktor.plugin").version("2.3.0").apply(false)
    id("app.cash.sqldelight").version("2.0.0-alpha05").apply(false)
    kotlin("android").version("1.7.10").apply(false)
    kotlin("multiplatform").version("1.7.10").apply(false)
    kotlin("plugin.serialization").version("1.6.10").apply(false)
//    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
}