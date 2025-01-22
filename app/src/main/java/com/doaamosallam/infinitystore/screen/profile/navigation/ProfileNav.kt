package com.doaamosallam.infinitystore.screen.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.profile.ProfileContainer

fun NavGraphBuilder.profileNav(navController: NavController) {
    composable(Screen.ProfileScreen.route) { ProfileContainer(navController) }
}

//fun NavGraphBuilder.profileNav(navController: NavController) {
//    composable(
//        route = Screen.ProfileScreen.route,
//        arguments = listOf(navArgument("userName") { type = NavType.StringType }) // Define the argument
//    ) { backStackEntry ->
//        val userName = backStackEntry.arguments?.getString("userName") ?: "" // Retrieve the userName
//        ProfileContainer(navController = navController, userName = userName) // Pass it to ProfileContainer
//    }
//}