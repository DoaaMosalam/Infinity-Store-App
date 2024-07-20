package com.doaamosallam.infinitystore.screen.category_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.domain.models.categories.CategoriesItem
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar
import com.doaamosallam.infinitystore.screen.home_screen.CategoriesItem
import com.doaamosallam.infinitystore.ui.theme.playWriteThin
import com.doaamosallam.infinitystore.viewmodel.categories.CategoriesViewModel
import com.doaamosallam.infinitystore.viewmodel.categories.CategoriesViewState

//state hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CategoryContainer(
    navController: NavController,
    categoriesViewModel: CategoriesViewModel = hiltViewModel()
) {
    val categoriesState by categoriesViewModel.viewState.collectAsState()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        when (categoriesState) {
            is CategoriesViewState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            is CategoriesViewState.Success -> {
                CategoryScreen(
                    categories = (categoriesState as CategoriesViewState.Success).data,
                    onClickCategories = {}
                )
            }
            is CategoriesViewState.Error -> {
                Text(
                    text = (categoriesState as CategoriesViewState.Error).message,
                    modifier = Modifier
//                        .align(Alignment.CenterHorizontally)
                )
            }
            CategoriesViewState.Idle -> {}
        }
    }
}

@Composable
fun CategoryScreen(
    categories: List<CategoriesItem>,
    onClickCategories: (CategoriesItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Categories",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 150.dp, top = 10.dp),
            fontSize = 20.sp,
            fontFamily = playWriteThin,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        DisplayProducts(
            categoriesItem = categories,
            onClickCategories = { onClickCategories(it) }
        )
    }
}

@Composable
fun DisplayProducts(
    categoriesItem: List<CategoriesItem>,
    onClickCategories: (CategoriesItem) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = categoriesItem) { categoriesItem ->
            CategoriesItem(
                category = categoriesItem,
                onClick = { onClickCategories(categoriesItem) }
            )
        }
    }
}

@Composable
fun CategoriesItem(
    category: CategoriesItem,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = category.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
@Preview(showSystemUi = true)
fun PreviewCategoryScreen() {
    CategoryScreen(
        categories = listOf(
            CategoriesItem(slug = "Beauty", name = "Beauty", url = "https://dummyjson.com/products/category/beauty"),
            CategoriesItem(slug = "Electronics", name = "Electronics", url = "https://dummyjson.com/products/category/fragrances"),
            CategoriesItem(slug = "Furniture", name = "Furniture", url = "https://dummyjson.com/products/category/furniture")
        ),
        onClickCategories = {}
    )
}