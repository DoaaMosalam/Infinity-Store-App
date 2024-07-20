package com.doaamosallam.infinitystore.screen.home

import android.annotation.SuppressLint
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.mapper.mapToCart
import com.doaamosallam.domain.models.categories.CategoryList
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.FullScreenLoading
import com.doaamosallam.infinitystore.compose.HeaderHome
import com.doaamosallam.infinitystore.compose.IconButtonHome
import com.doaamosallam.infinitystore.compose.SpacerGeneral
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.screen.home.event.HomeEvent


//state hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeContainer(navController: NavController) {

    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()


    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {

        FullScreenLoading(
            modifier = Modifier.fillMaxSize(),
            isLoading = uiState.isLoading,
        )


        HomeScreen(
            products = uiState.products,
            categoryList = uiState.categories,
            onClickMenu = {
                navController.navigate(Screen.CartScreen.route)
            },
            onClickProfile = {
                navController.navigate(Screen.ProfileScreen.route)
            },
            search = uiState.search,
            onSearchChange = viewModel::onSearchEvent,
            onClickProduct = {

            },
            onClickCart = viewModel::onAddProductToCart
        )

        if (uiState.error.isNotEmpty())
            Text(text = uiState.error)
    }

}


@Composable
private fun HomeScreen(
    products: List<Product>,
    categoryList: List<CategoryList>,
    onClickMenu: () -> Unit,
    onClickProfile: () -> Unit,
    search: String,
    onSearchChange: (String) -> Unit,
    onClickProduct: (Product) -> Unit,
    onClickCart: (Product) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp)
    ) {
        HomeDisplay(
            products = products,
            categoryList = categoryList,
            onClickMenu = onClickMenu,
            onClickProfile = onClickProfile,
            search = search,
            onSearchChange = { onSearchChange(it) },
            onClickProduct = onClickProduct,
            onClickCart = onClickCart
        )
    }
}

// function for display design home screen
@Composable
fun HomeDisplay(
    products: List<Product>,
    categoryList: List<CategoryList>,
    onClickMenu: () -> Unit,
    onClickProfile: () -> Unit,
    search: String,
    onSearchChange: (String) -> Unit,
    onClickProduct: ((Product)) -> Unit,
    onClickCart: (Product) -> Unit,
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
    // header title and subtitle
    HeaderHome(title = "Explore", subtitle = "Best trendy collection!")
    // search bar
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp),
        value = search,
        onValueChange = { onSearchChange(it) },

        label = { Text(text = stringResource(R.string.search)) },
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null
            )
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = KeyboardType.Text,
        ),
        visualTransformation = VisualTransformation.None
    )

    SpacerGeneral(Spacer(modifier = Modifier.height(16.dp)))
    // display category
    DisplayCategory(categoryList)
    SpacerGeneral(Spacer(modifier = Modifier.height(16.dp)))
    // display products
    DisplayProducts(
        products = products,
        onClickProduct = onClickProduct,
        onClickCart = onClickCart
    )


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayProducts(
    products: List<Product>,
    onClickProduct: (Product) -> Unit,
    onClickCart: (Product) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(
            items = products
        ) { product ->
            ProductItem(
                product = product,
                onClickProduct = { onClickProduct(product) },
                onClickCart = onClickCart,
                modifier = Modifier.animateItemPlacement()
            )
        }

    }
}

@Composable
fun DisplayCategory(categoryList: List<CategoryList>) {
    val collectionsList = categoryList.map { it.name }
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


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        products = emptyList(),
        categoryList = emptyList(),
        onClickMenu = {},
        onClickProfile = {},
        search = "",
        onSearchChange = {},
        onClickProduct = {},
        onClickCart = {}
    )
}
