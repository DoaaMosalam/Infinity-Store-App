package com.doaamosallam.infinitystore.screen.home_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.CategoryItem
import com.doaamosallam.infinitystore.compose.HeaderHome
import com.doaamosallam.infinitystore.compose.IconButtonHome
import com.doaamosallam.infinitystore.compose.ProductItem
import com.doaamosallam.infinitystore.compose.SpacerGeneral
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.viewmodel.home.HomeViewModel
import com.doaamosallam.infinitystore.viewmodel.home.HomeViewState

//state hoisting
@Composable
fun HomeContainer(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val state by homeViewModel.viewState.collectAsState()
    when (state) {
        is HomeViewState.Loading -> {
            // Display loading indicator
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is HomeViewState.Success -> {
            HomeScreen(
                products = (state as HomeViewState.Success).products,
                onClickMenu = {
                    navController.navigate(Screen.CartScreen.route)
                },
                onClickProfile = {
                    navController.navigate(Screen.ProfileScreen.route)
                }
            )
        }

        is HomeViewState.Error -> {
            // Display error message
            Text(text = (state as HomeViewState.Error).message)
        }

        else -> {}
    }

}


@Composable
private fun HomeScreen(
    products: List<Product>,
    onClickMenu: () -> Unit,
    onClickProfile: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        HomeDisplay(
            products = products,
            onClickMenu = onClickMenu,
            onClickProfile = onClickProfile
        )
    }
}

// function for display design home screen
@Composable
fun HomeDisplay(
    products: List<Product>,
    onClickMenu: () -> Unit,
    onClickProfile: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButtonHome(
            onClick = onClickMenu,
            painter = painterResource(id = R.drawable.menu),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )

        SpacerGeneral(Spacer(modifier = Modifier.weight(1f)))

        IconButtonHome(
            onClick = onClickProfile,
            painter = painterResource(id = R.drawable.profile_icon),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
    HeaderHome(title = "Explore", subtitle = "Best trendy collection!")

    SpacerGeneral(Spacer(modifier = Modifier.height(16.dp)))
    // display category
    DisplayCategory()
    SpacerGeneral(Spacer(modifier = Modifier.height(16.dp)))
    // display products
    DisplayProducts(products)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayProducts(
    products: List<Product>
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = products
        ) { product ->
            ProductItem(
                product = product,
                onClick = {},
                modifier = Modifier.animateItemPlacement()
            )
        }
    }
}

@Composable
fun DisplayCategory() {
    val collectionsList = categoryList()
    var selectedCollection by remember { mutableStateOf("All") }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 6.dp)
    ) {
        items(collectionsList) { collection ->
            CategoryItem(
                collection = collection,
                isSelected = collection == selectedCollection,
                onClick = { selected ->
                    selectedCollection = selected
                }
            )
        }
    }
}


@Composable
fun categoryList(): List<String> {
    return listOf(
        "All",
        "beauty",
        "fragrances",
        "furniture",
        "groceries",
        "home-decoration",
        "kitchen-accessories",
        "laptops",
        "mens-shirts",
        "mens-shoes",
        "mens-watches",
        "mobile-accessories",
        "motorcycle",
        "skin-care",
        "smartphones",
        "sports-accessories",
        "sunglasses",
        "tablets",
        "tops",
        "vehicle",
        "womens-bags",
        "womens-dresses",
        "womens-jewellery",
        "womens-shoes",
        "womens-watches"
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        products = emptyList(),
        onClickMenu = {},
        onClickProfile = {}
    )
}
