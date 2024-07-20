package com.doaamosallam.infinitystore.screen.cart.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.cart.CartContainer

fun NavGraphBuilder.cartRoute(navController: NavController) {
    composable(Screen.CartScreen.route) {
        CartContainer(navController)
    }
}