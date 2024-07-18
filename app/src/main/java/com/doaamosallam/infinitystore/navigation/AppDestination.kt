package com.doaamosallam.infinitystore.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import com.doaamosallam.infinitystore.R

sealed class Screen(val route: String, val icon: Int? =null, val title:String?=null) {
    // app destination
    data object Register : Screen("RegisterScreen")
    data object Login : Screen("LoginScreen")
    data object ForgetPassword : Screen("forget_password_screen")
    data object HomeScreen : Screen("HomeScreen",R.drawable.outline_home_24,"Home")
    data object CategoryScreen : Screen("CategoryScreen", R.drawable.outline_category_24,"Category")
    data object FavoriteScreen:Screen("FavoriteScreen",R.drawable.baseline_favorite_border_24,"Favorite")
    data object CartScreen : Screen("CartScreen",R.drawable.outline_shopping_bag_24,"Cart")
    data object ProfileScreen : Screen("ProfileScreen",R.drawable.person_outline_24,"Profile")
}