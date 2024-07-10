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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//                // Your UI content
            InfinityApp()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {

}


