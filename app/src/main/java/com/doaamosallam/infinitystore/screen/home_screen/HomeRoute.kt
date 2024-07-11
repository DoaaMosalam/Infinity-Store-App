package com.doaamosallam.infinitystore.screen.home_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.util.Constant


fun NavGraphBuilder.HomeRoute(navController: NavController) {
    composable(Constant.HomeScreen) { HomeContainer() }
}