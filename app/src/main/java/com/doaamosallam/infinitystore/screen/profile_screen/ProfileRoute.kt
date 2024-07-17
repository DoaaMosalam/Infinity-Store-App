package com.doaamosallam.infinitystore.screen.profile_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen

fun NavGraphBuilder.ProfileRoute(navController: NavController) {
    composable(Screen.ProfileScreen.route) { ProfileContainer(navController) }
}