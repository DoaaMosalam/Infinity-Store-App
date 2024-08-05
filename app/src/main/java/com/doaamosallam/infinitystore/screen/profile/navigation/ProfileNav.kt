package com.doaamosallam.infinitystore.screen.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.profile.ProfileContainer

fun NavGraphBuilder.profileNav(navController: NavController) {
    composable(Screen.ProfileScreen.route) { ProfileContainer(navController) }
}