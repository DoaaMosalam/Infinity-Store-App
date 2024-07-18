package com.doaamosallam.infinitystore.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.screen.cart_screen.CartContainer
import com.doaamosallam.infinitystore.screen.cart_screen.CartRoute
import com.doaamosallam.infinitystore.screen.category_screen.CategoryContainer
import com.doaamosallam.infinitystore.screen.forget_password_screen.ForgetPasswordRoute
import com.doaamosallam.infinitystore.screen.home_screen.HomeRoute
import com.doaamosallam.infinitystore.screen.login_screen.LoginRoute
import com.doaamosallam.infinitystore.screen.profile_screen.ProfileRoute
import com.doaamosallam.infinitystore.screen.register_screen.RegisterRoute

@Composable
fun InfinityNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
        RegisterRoute(navController)
        LoginRoute(navController)
        ForgetPasswordRoute(navController)
        HomeRoute(navController)
        composable(Screen.CategoryScreen.route) { CategoryContainer(navController) }
      CartRoute(navController)
        ProfileRoute(navController)

    }
}