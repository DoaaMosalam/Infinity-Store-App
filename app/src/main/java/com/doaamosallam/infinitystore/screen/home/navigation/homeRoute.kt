package com.doaamosallam.infinitystore.screen.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.home.HomeContainer


fun NavGraphBuilder.homeRoute(navController: NavController) {
    composable(Screen.HomeScreen.route) { HomeContainer(navController) }
}