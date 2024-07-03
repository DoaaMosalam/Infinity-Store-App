package com.doaamosallam.infinitystore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.doaamosallam.domain.models.Login
import com.doaamosallam.infinitystore.compose.screen.LoginScreen
import com.doaamosallam.infinitystore.viewmodel.Login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val loginData = Login(email = "Doaa.Mosalam@example.com", password = "1234")
        viewModel.login(loginData)
        Log.d("Login", "Save data: $loginData")

        setContent {

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {

}


