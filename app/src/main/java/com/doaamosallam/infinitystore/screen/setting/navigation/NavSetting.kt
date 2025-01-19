package com.doaamosallam.infinitystore.screen.setting.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.setting.SettingContainer

fun NavGraphBuilder.settingNav(navController: NavController) {
    composable(Screen.SettingScreen.route) { SettingContainer(navController) }
}