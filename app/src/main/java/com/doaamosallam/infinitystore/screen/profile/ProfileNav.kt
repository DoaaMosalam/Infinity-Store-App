package com.doaamosallam.infinitystore.screen.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen

fun NavGraphBuilder.profileNav(navController: NavController) {
    composable(Screen.ProfileScreen.route) { ProfileContainer(navController) }
}