package com.doaamosallam.infinitystore.screen.login_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen

fun NavGraphBuilder.LoginRoute(navController: NavController) {
    composable(Screen.Login.route) { LoginUser(navController) }
}