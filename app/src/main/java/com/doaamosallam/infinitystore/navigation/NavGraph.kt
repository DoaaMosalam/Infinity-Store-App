package com.doaamosallam.infinitystore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.doaamosallam.infinitystore.screen.forget_password_screen.ForgetPasswordRoute
import com.doaamosallam.infinitystore.screen.home_screen.HomeRoute
import com.doaamosallam.infinitystore.screen.login_screen.LoginRoute
import com.doaamosallam.infinitystore.screen.register_screen.RegisterRoute

private const val register = "RegisterScreen"

@Composable
fun InfinityNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = register) {
        RegisterRoute(navController)
        LoginRoute(navController)
        ForgetPasswordRoute(navController)
        HomeRoute(navController)
    }
}