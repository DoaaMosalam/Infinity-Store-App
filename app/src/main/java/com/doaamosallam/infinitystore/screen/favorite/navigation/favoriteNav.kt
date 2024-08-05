package com.doaamosallam.infinitystore.screen.favorite.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.favorite.FavoriteContainer

fun NavGraphBuilder.favoriteNav(navController: NavController) {
    composable(Screen.FavoriteScreen.route) { FavoriteContainer(navController) }
}