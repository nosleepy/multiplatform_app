package com.grandstream.multiplatform

import androidx.compose.runtime.Composable
import app.cash.sqldelight.db.SqlDriver
import com.grandstream.multiplatform.di.DatabaseWrapper
import com.grandstream.multiplatform.screen.ContactScreen
import com.grandstream.multiplatform.screen.LoginScreen
import com.grandstream.multiplatform.screen.MainScreen
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun Navigation(sqlDriver: SqlDriver) {
    DatabaseWrapper.init(sqlDriver)
    val navigator = rememberNavigator()
    NavHost(
        navigator = navigator,
        initialRoute = "/main"
    ) {
        scene("/login") {
            LoginScreen(navigator)
        }
        scene("/contact") {
            ContactScreen()
        }
        scene("/main") {
            MainScreen(navigator)
        }
    }
}