plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser()
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation("org.jetbrains.kotlinx:kotlinx-html-js:0.8.0")
                implementation(compose.ui)
                implementation(compose.web.core)
                implementation(compose.runtime)
            }
        }
    }
}

compose.experimental {
    web.application {}
}