package com.doaamosallam.infinitystore.screen.register.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.register.RegisterUser


fun NavGraphBuilder.registerNav(navController: NavController) {
    composable(Screen.Register.route) { RegisterUser(navController) }
}