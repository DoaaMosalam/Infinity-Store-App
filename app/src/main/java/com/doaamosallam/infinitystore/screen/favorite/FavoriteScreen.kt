package com.doaamosallam.infinitystore.screen.favorite

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.compose.HeaderHome
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FavoriteContainer(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        // do  code here category screen
        FavoriteScreen()
    }
}

@Composable
fun FavoriteScreen() {
    FavoriteDisplay()

}

@Composable
fun FavoriteDisplay() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        HeaderHome(title = "Setting Screen", subtitle = "")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPCategoryScreen() {
    FavoriteScreen()
}