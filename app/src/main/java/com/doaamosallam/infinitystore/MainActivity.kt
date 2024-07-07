package com.doaamosallam.infinitystore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.doaamosallam.domain.models.Login
import com.doaamosallam.infinitystore.screen.LoginUser
import com.doaamosallam.infinitystore.screen.RegisterUser
import com.doaamosallam.infinitystore.ui.theme.InfinityStoreTheme
import com.doaamosallam.infinitystore.viewmodel.Login.LoginIntent
import com.doaamosallam.infinitystore.viewmodel.Login.LoginViewModel
import com.doaamosallam.infinitystore.viewmodel.Login.LoginViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val loginData = Login(email = "Doaa.Mosalam@example.com", password = "1234")
//        viewModel.login(loginData)
//        Log.d("Login", "Save data: $loginData")
        setContent {
//            InfinityStoreTheme {
//                val loginViewModel: LoginViewModel by viewModels()
//
//                // Hard code login data
//                val loginData = Login(email = "Doaa.Mosalam@example.com", password = "1234")
//
//                // Trigger login event
//                loginViewModel.handleIntent(LoginIntent.Login(email = loginData.email, password = loginData.password))
//
//                Log.d("Login", "Save data: $loginData")
//
//                // Observe state changes (this is optional, just for demonstration)
//                val viewState = loginViewModel.viewState.collectAsState().value
//                when (viewState) {
//                    is LoginViewState.Loading -> Log.d("Login", "Loading...")
//                    is LoginViewState.Success -> Log.d("Login", "Login Successful: ${viewState.login.email}")
//                    is LoginViewState.Error -> Log.d("Login", "Error: ${viewState.message}")
//                    else -> Log.d("Login", "Idle")
//                }
//
//                // Your UI content
//                LoginUser()
            RegisterUser()
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {

}


