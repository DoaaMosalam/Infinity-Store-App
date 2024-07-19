package com.doaamosallam.infinitystore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.doaamosallam.infinitystore.navigation.InfinityApp
import dagger.hilt.android.AndroidEntryPoint

private const val register = "RegisterScreen"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // Your UI content
            InfinityApp()

        }
    }
}


