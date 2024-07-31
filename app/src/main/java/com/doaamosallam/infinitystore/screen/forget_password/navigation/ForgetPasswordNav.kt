package com.doaamosallam.infinitystore.screen.forget_password.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.forget_password.ForgetPassword

fun NavGraphBuilder.forgetPasswordNav(navController: NavController) {
    composable(Screen.ForgetPassword.route) { ForgetPassword(navController) }

}