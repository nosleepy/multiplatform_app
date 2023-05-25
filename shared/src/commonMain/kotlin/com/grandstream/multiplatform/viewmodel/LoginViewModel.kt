package com.grandstream.multiplatform.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grandstream.multiplatform.util.LogUtil
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import moe.tlaster.precompose.navigation.NavOptions
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.navigation.PopUpTo
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

class LoginViewModel: ViewModel() {
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var result by mutableStateOf("")

    fun login(navigator: Navigator) {
        LogUtil.d("username = $username, password = $password")
        if (username.isEmpty()) {
            result = "username is empty"
            return
        } else if (password.isEmpty()) {
            result = "password is empty"
            result
        }
        if (username.trim() == "admin" && password.trim() == "123456") {
            result = "success"
            viewModelScope.launch {
                navigator.navigate(
                    route = "/main",
                    options = NavOptions(
                        popUpTo = PopUpTo(
                            route = "/login",
                            inclusive = true,
                        )
                    )
                )
                delay(500)
                username = ""
                password = ""
                result = ""
            }
        } else {
            result = "fail"
        }
    }
}