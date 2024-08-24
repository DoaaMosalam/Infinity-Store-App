package com.doaamosallam.infinitystore.navigation

import com.doaamosallam.infinitystore.R

sealed class Screen(val route: String, val icon: Int? = null, val title: String? = null) {
    // app destination
    data object Register : Screen("RegisterScreen")
    data object Login : Screen("LoginScreen")
    data object ForgetPassword : Screen("forget_password_screen")
    data object HomeScreen : Screen("HomeScreen", R.drawable.outline_home_24, "Home")
    data object CategoryScreen : Screen("CategoryScreen", R.drawable.outline_category_24, "Category")

    data object CartScreen : Screen("CartScreen", R.drawable.outline_shopping_bag_24, "Cart")
    data object SettingScreen : Screen("SettingScreen", R.drawable.baseline_settings_24, "Setting")
    data object ProfileScreen : Screen("ProfileScreen")
    data object PaymentScreen : Screen("PaymentScreen")
    data object FavoriteScreen : Screen("FavoriteScreen")
    data object CheckoutScreen : Screen("CheckoutScreen")

}