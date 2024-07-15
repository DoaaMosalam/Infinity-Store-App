package com.doaamosallam.infinitystore.screen.home_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.util.Screen


fun NavGraphBuilder.HomeRoute(navController: NavController) {
    composable(Screen.HomeScreen.route) { HomeContainer(navController) }
}