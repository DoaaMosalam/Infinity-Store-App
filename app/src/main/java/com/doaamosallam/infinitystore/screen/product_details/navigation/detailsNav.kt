package com.doaamosallam.infinitystore.screen.product_details.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.product_details.ProductDetailsContainer

fun NavGraphBuilder.detailsNav(navController: NavController) {
    composable(Screen.ProductDetailsScreen.route) { ProductDetailsContainer(navController) }
}