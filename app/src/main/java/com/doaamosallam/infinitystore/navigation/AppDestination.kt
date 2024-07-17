package com.doaamosallam.infinitystore.navigation

sealed class Screen(val route: String) {
    // app destination
    data object Register : Screen("RegisterScreen")
    data object Login : Screen("LoginScreen")
    data object ForgetPassword : Screen("forget_password_screen")
    data object HomeScreen : Screen("HomeScreen")
    data object CartScreen : Screen("CartScreen")
    data object CategoryScreen : Screen("CategoryScreen")
    data object ProfileScreen : Screen("ProfileScreen")
}