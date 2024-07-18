package com.doaamosallam.infinitystore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint

private const val register = "RegisterScreen"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Your UI content
            InfinityApp()

//            InfinityStoreTheme {
//                val navController = rememberNavController()
//
////                NavHost(navController = navController, startDestination = register) {
////                    RegisterRoute(navController)
////                    LoginRoute(navController)
////                    ForgetPasswordRoute(navController)
////                    HomeRoute(navController)
////                    composable(Screen.CategoryScreen.route) { CategoryScreen() }
////                    composable(Screen.CartScreen.route) { CartScreen() }
////                    ProfileRoute(navController)
////                }
//                HomeContainer(navController = navController)
//            }


        }
    }
}


