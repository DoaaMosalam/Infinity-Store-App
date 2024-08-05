package com.doaamosallam.infinitystore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.doaamosallam.infinitystore.navigation.InfinityApp
import com.doaamosallam.infinitystore.ui.theme.InfinityStoreTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Your UI content
//            InfinityApp()

            InfinityStoreTheme(
                darkTheme = true,
                dynamicColor = true,
                content = {
                    InfinityApp()
                }
            )
        }
    }
}


