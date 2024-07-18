package com.doaamosallam.infinitystore.screen.category_screen

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

//state hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryContainer(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        // do  code here category screen
    CategoryScreen()
    }
}

@Composable
fun CategoryScreen() {
    CategoryDisplay()

}

@Composable
fun CategoryDisplay() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        HeaderHome(title = "Category Screen", subtitle = "")
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPCategoryScreen() {
    CategoryScreen()
}