package com.doaamosallam.infinitystore.screen.cart_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Delete
import com.doaamosallam.domain.models.categories.CategoriesItem
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar
import com.doaamosallam.infinitystore.screen.home_screen.CartItem
import com.doaamosallam.infinitystore.screen.home_screen.CategoriesItem
import com.doaamosallam.infinitystore.ui.theme.playWriteThin

//state hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartContainer(navController: NavController) {
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
       CartScreen(
           cart = listOf(
               CategoriesItem(slug = "Beauty", name = "Beauty", url = "https://dummyjson.com/products/category/beauty"),
               CategoriesItem(slug = "Electronics", name = "Electronics", url = "https://dummyjson.com/products/category/fragrances"),
               CategoriesItem(slug = "Furniture", name = "Furniture", url = "https://dummyjson.com/products/category/furniture")
           ),
           onClickDelete = {}
       )
    }
}

@Composable
fun CartScreen(
    cart: List<CategoriesItem>,
    onClickDelete:()->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Cart",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 100.dp, top = 10.dp),
            fontSize = 50.sp,
            fontFamily = playWriteThin,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        DisplayProducts(
            cartsItem = listOf(
                CategoriesItem(
                    slug = "Beauty",
                    name = "Beauty",
                    url = "https://dummyjson.com/products/category/beauty"
                ),
                CategoriesItem(
                    slug = "Electronics",
                    name = "Electronics",
                    url = "https://dummyjson.com/products/category/fragrances"
                ),
                CategoriesItem(
                    slug = "Furniture",
                    name = "Furniture",
                    url = "https://dummyjson.com/products/category/furniture"
                )
            ),
            onClickCategories = { onClickDelete() }
        )
    }

}

@Composable
fun DisplayProducts(
    cartsItem: List<CategoriesItem>,
    onClickCategories: (CategoriesItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = cartsItem) { categoriesItem ->
            CartItem(
                cart = categoriesItem,
                onClick = { onClickCategories(categoriesItem) }
            )
        }
    }
}


@Composable
@Preview(showSystemUi = true)
fun PreviewProfileScreen() {
    CartScreen(
        cart = listOf(
            CategoriesItem(slug = "Beauty", name = "Beauty", url = "https://dummyjson.com/products/category/beauty"),
            CategoriesItem(slug = "Electronics", name = "Electronics", url = "https://dummyjson.com/products/category/fragrances"),
            CategoriesItem(slug = "Furniture", name = "Furniture", url = "https://dummyjson.com/products/category/furniture")
        ),
        onClickDelete = {}
    )
}