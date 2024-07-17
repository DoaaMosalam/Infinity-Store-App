package com.doaamosallam.infinitystore.screen.forget_password_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen

fun NavGraphBuilder.ForgetPasswordRoute(navController: NavController) {
    composable(Screen.ForgetPassword.route) { ForgetPassword(navController) }

}