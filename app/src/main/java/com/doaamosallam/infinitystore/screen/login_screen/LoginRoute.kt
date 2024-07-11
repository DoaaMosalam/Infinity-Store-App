package com.doaamosallam.infinitystore.screen.login_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.util.Constant

fun NavGraphBuilder.LoginRoute(navController: NavController) {
    composable(Constant.LoginScreen) { LoginUser(navController) }
}