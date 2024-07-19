package com.doaamosallam.infinitystore.screen.category_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.doaamosallam.infinitystore.compose.HeaderHome
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar
import com.doaamosallam.infinitystore.screen.home_screen.CategoriesItem
import com.doaamosallam.infinitystore.screen.home_screen.ProductItem

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        CategoryDisplay()
    }


}

@Composable
fun CategoryDisplay() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
//        DisplayCategories(categories)

    }
}
//@Composable
//fun DisplayCategories(categories:List<Categories>){
//    LazyVerticalGrid(columns = GridCells.Fixed(count = 2),
//        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
//        modifier = Modifier.fillMaxSize()) {
//
//        items(
//            items = categories
//        ) { category ->
//            CategoriesItem(category = category,
//                onClick = { /*TODO*/ },
//                modifier = Modifier.animateItemPlacement()
//                )
//        }
//
//    }
//
//}

@Composable
@Preview(showBackground = true)
fun PreviewPCategoryScreen() {
    CategoryScreen()
}