package com.doaamosallam.infinitystore.screen.menu.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.menu.MenuContainer

fun NavGraphBuilder.menuNav(navController: NavController){
    composable(Screen.MenuScreen.route){MenuContainer(navController)}
}