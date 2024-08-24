package com.doaamosallam.infinitystore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.screen.cart.PaymentContainer
import com.doaamosallam.infinitystore.screen.cart.navigation.cartNav
import com.doaamosallam.infinitystore.screen.category.CategoryContainer
import com.doaamosallam.infinitystore.screen.favorite.navigation.favoriteNav
import com.doaamosallam.infinitystore.screen.forget_password.navigation.forgetPasswordNav
import com.doaamosallam.infinitystore.screen.home.navigation.homeRoute
import com.doaamosallam.infinitystore.screen.login.navigation.loginNav
import com.doaamosallam.infinitystore.screen.profile.navigation.profileNav
import com.doaamosallam.infinitystore.screen.register.navigation.registerNav
import com.doaamosallam.infinitystore.screen.setting.SettingContainer

@Composable
fun InfinityNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {

        registerNav(navController)

        loginNav(navController)

        forgetPasswordNav(navController)

        homeRoute(navController)

        composable(Screen.CategoryScreen.route) { CategoryContainer(navController) }

        cartNav(navController)

        composable(Screen.SettingScreen.route) { SettingContainer(navController) }

        profileNav(navController)
        favoriteNav(navController)

        composable(Screen.PaymentScreen.route) { PaymentContainer(navController) }

    }
}