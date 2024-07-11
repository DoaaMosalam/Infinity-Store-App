package com.doaamosallam.infinitystore.screen.register_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.util.Constant


fun NavGraphBuilder.RegisterRoute(navController: NavController) {
    composable(Constant.RegisterScreen) { RegisterUser(navController) }
}