package com.doaamosallam.infinitystore

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doaamosallam.infinitystore.screen.ForgetPassword
import com.doaamosallam.infinitystore.screen.HomeContainer
import com.doaamosallam.infinitystore.screen.LoginUser
import com.doaamosallam.infinitystore.screen.RegisterUser
import com.doaamosallam.infinitystore.ui.theme.InfinityStoreTheme
import com.doaamosallam.infinitystore.util.AppDestination

@Composable
fun InfinityApp() {
    InfinityStoreTheme {
       val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "RegisterScreen") {
            composable(AppDestination.RegisterScreen){ RegisterUser(navController)}
            composable(AppDestination.LoginScreen){ LoginUser(navController) }
            composable(AppDestination.ForgetPassword){ ForgetPassword(navController) }
            composable(AppDestination.HomeScreen){ HomeContainer() }
            
        }
    }
}
