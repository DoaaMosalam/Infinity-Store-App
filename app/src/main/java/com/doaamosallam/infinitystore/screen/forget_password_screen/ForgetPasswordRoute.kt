package com.doaamosallam.infinitystore.screen.forget_password_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.util.Constant

fun NavGraphBuilder.ForgetPasswordRoute(navController: NavController) {
    composable(Constant.ForgetPassword) { ForgetPassword(navController) }
}