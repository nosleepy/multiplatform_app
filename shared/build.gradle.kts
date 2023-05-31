plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("app.cash.sqldelight")
//    id("io.ktor.plugin")
}

val ktorVersion = "2.2.4"

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
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                api("io.ktor:ktor-client-core:$ktorVersion")
                api("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                api("io.ktor:ktor-client-logging:$ktorVersion")
                api("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation("app.cash.sqldelight:runtime:2.0.0-alpha05")
                implementation("app.cash.sqldelight:coroutines-extensions:2.0.0-alpha05")
                api("io.github.aakira:napier:2.6.1")
                api("moe.tlaster:precompose:1.4.1")
//                api("moe.tlaster:precompose-molecule:1.4.1")
                api("moe.tlaster:precompose-viewmodel:1.4.1")
                api("io.insert-koin:koin-core:3.4.0")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")
                api("androidx.compose.ui:ui-tooling-preview:1.2.1")
                api("com.blankj:utilcode:1.30.7")
                implementation("io.ktor:ktor-client-android:$ktorVersion")
                implementation("app.cash.sqldelight:android-driver:2.0.0-alpha05")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktorVersion")
                implementation("app.cash.sqldelight:sqljs-driver:2.0.0-alpha05")
                implementation(npm("sql.js", "1.6.2"))
                implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-java:$ktorVersion")
                implementation("app.cash.sqldelight:sqlite-driver:2.0.0-alpha05")
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