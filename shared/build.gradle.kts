plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("app.cash.sqldelight")
//    id("io.ktor.plugin")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    jvm("desktop")

    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(compose.ui)
                api(compose.foundation)
                api(compose.material)
                api(compose.runtime)
//                implementation(compose.preview)
//                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
//                implementation(compose.material3)
                with(Deps.Ktor) {
                    api(clientCore)
                    api(clientNegotiation)
                    api(clientLogging)
                    api(json)
                }
                with(Deps.SqlDelight) {
                    implementation(runtime)
                    implementation(coroutinesExtensions)
                }
                with(Deps.Kotlinx) {
                    api(coroutinesCore)
                }
                with(Deps.Koin) {
                    api(koinCore)
                }
                with(Deps.PreCompose) {
                    api(core)
                    api(viewmodel)
                }
                with(Deps.Napier) {
                    api(core)
                }
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Deps.Ktor.clientAndroid)
                implementation(Deps.SqlDelight.androidDriver)
                api(Deps.Androidx.activityCompose)
                api(Deps.Androidx.coreKtx)
                api(Deps.Androidx.appcompat)
                api(Deps.Androidx.preview)
                api("com.blankj:utilcode:1.30.7")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(Deps.Ktor.clientDarwin)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val jsMain by getting {
            dependencies {
                implementation(Deps.Ktor.clientJs)
                implementation(Deps.SqlDelight.jsDriver)
                implementation(npm("sql.js", "1.6.2"))
                implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(Deps.Ktor.clientJava)
                implementation(Deps.SqlDelight.javaDriver)
            }
        }
    }
}

sqldelight {
    databases {
        create("ContactDatabase") {
            packageName.set("com.grandstream.multiplatform.database")
        }
    }
}

android {
    namespace = "com.grandstream.multiplatform"
    compileSdk = 33
    defaultConfig {
        minSdk = 21
        targetSdk = 33
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}