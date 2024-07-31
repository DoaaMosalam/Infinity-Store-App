package com.doaamosallam.infinitystore.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.doaamosallam.infinitystore.ui.theme.InfinityStoreTheme

@Composable
fun InfinityApp() {
    InfinityStoreTheme {
        val navController = rememberNavController()
        InfinityNavGraph(navController = navController)
    }
}



