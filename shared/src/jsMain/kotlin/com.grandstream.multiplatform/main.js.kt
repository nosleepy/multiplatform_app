package com.grandstream.multiplatform

import androidx.compose.runtime.Composable
import app.cash.sqldelight.db.SqlDriver

@Composable
fun MainView(sqlDriver: SqlDriver) = Navigation(sqlDriver)