package com.doaamosallam.infinitystore.screen.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.login.LoginUser

fun NavGraphBuilder.loginNav(navController: NavController) {
    composable(Screen.Login.route) { LoginUser(navController) }
}