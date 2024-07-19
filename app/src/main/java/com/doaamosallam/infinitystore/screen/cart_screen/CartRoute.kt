package com.doaamosallam.infinitystore.screen.cart_screen

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen

fun NavGraphBuilder.CartRoute(navController: NavController) {
    composable(Screen.CartScreen.route) { CartContainer(navController) }
}