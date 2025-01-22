package com.doaamosallam.infinitystore.screen.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.domain.models.favorite.FavoriteProduct
import com.doaamosallam.infinitystore.compose.FullScreenLoading
import com.doaamosallam.infinitystore.compose.ProductFavoriteItem
import com.doaamosallam.infinitystore.compose.SpacerGeneral
import com.doaamosallam.infinitystore.compose.TopBarFavorite
import com.doaamosallam.infinitystore.navigation.Screen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Composable
fun FavoriteContainer(
    navController: NavController,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.isLoading) {
        FullScreenLoading(
            modifier = Modifier.fillMaxSize(),
            isLoading = uiState.isLoading
        )
    } else {
        FavoriteScreen(
            product = uiState.favorite,
            onclickDelete = { product ->
                viewModel.onRemoveProductFavorite(product)
            },

            onClickBack = { navController.popBackStack() },
            onClickCart = { navController.navigate(Screen.CartScreen.route) }

        )
    }
    if (uiState.error.isNotEmpty()) {
        Text(
            text = uiState.error,
            color = Color.Red,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            fontSize = 18.sp
        )
    }
}

@Composable
fun FavoriteScreen(
    product: Flow<List<FavoriteProduct>>,
    onclickDelete: (FavoriteProduct) -> Unit,
    onClickBack: () -> Unit,
    onClickCart: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        val favoriteItems by product.collectAsState(initial = emptyList())

        TopBarFavorite(
            onClickBack = onClickBack,
            onclickCart = onClickCart
        )
        SpacerGeneral(modifier = Modifier.weight(1f))
        DisplayProducts(
            items = favoriteItems,
            onClickDelete = onclickDelete,
        )
    }

}

@Composable
fun DisplayProducts(
    items: List<FavoriteProduct>,
    onClickDelete: (FavoriteProduct) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(items) { favoriteItem ->
            ProductFavoriteItem(
                product = favoriteItem,
                onClickDelete = { onClickDelete(favoriteItem) },
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))

        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewPCategoryScreen() {
    val sampleCartProducts = listOf(
        FavoriteProduct(
            id = 1,
            title = "Leather Tangerine Coat",
            price = 257.85,

            thumbnail = "https://example.com/image1.jpg",
            discountPercentage = 10.0,
            rating = 4.5,
            discountedTotal = 232.07
        ),
        FavoriteProduct(
            id = 2,
            title = "Blue Denim Jacket",
            price = 150.00,
            thumbnail = "https://example.com/image2.jpg",
            rating = 4.5,
            discountPercentage = 5.0,
            discountedTotal = 285.00,

            )
    )
    FavoriteScreen(
        product = flowOf(sampleCartProducts),
        onClickBack = {},
        onclickDelete = {},
        onClickCart = {}


    )
}