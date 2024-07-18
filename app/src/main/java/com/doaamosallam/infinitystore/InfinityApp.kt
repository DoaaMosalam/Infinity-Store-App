package com.doaamosallam.infinitystore

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar
import com.doaamosallam.infinitystore.navigation.InfinityNavGraph
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.cart_screen.CartContainer
import com.doaamosallam.infinitystore.screen.category_screen.CategoryContainer
import com.doaamosallam.infinitystore.screen.favorite_screen.FavoriteContainer
import com.doaamosallam.infinitystore.screen.home_screen.HomeContainer
import com.doaamosallam.infinitystore.screen.profile_screen.ProfileContainer
import com.doaamosallam.infinitystore.ui.theme.InfinityStoreTheme

@Composable
fun InfinityApp() {
    InfinityStoreTheme {
        val navController = rememberNavController()
        InfinityNavGraph(navController = navController)
    }
}



