package com.grandstream.multiplatform.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grandstream.multiplatform.util.ColorUtil
import com.grandstream.multiplatform.viewmodel.LoginViewModel
import moe.tlaster.precompose.navigation.Navigator
import moe.tlaster.precompose.viewmodel.viewModel

@Composable
fun LoginScreen(navigator: Navigator) {
    val loginViewModel = viewModel { LoginViewModel() }
    Column(modifier = Modifier.fillMaxSize()) {
        TitleBar(title = "Login")
        Divider(thickness = 1.dp, modifier = Modifier.padding(horizontal = 20.dp).background(Color.Red))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(modifier = Modifier.width(420.dp)) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = loginViewModel.username,
                    onValueChange = { loginViewModel.username = it },
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                    placeholder = { Text("username") }
                )
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = loginViewModel.password,
                    onValueChange = { loginViewModel.password = it },
                    colors = TextFieldDefaults.outlinedTextFieldColors(),
                    textStyle = TextStyle.Default.copy(fontSize = 18.sp),
                    placeholder = { Text("password") }
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = ColorUtil.green,
                        contentColor = Color.White
                    ),
                    onClick = { loginViewModel.login(navigator) },
                ) {
                    Text(text = "Login")
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = loginViewModel.result)
            }
        }
    }
}