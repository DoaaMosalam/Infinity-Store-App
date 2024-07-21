package com.doaamosallam.infinitystore.screen.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.infinitystore.compose.FullScreenLoading
import com.doaamosallam.infinitystore.navigation.BottomNavigationBar
import com.doaamosallam.infinitystore.compose.CartItem
import com.doaamosallam.infinitystore.ui.theme.Merri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

//state hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartContainer(navController: NavController) {
    val viewModel: CartViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        if (uiState.isLoading) {
            FullScreenLoading(
                modifier = Modifier.fillMaxSize(),
                isLoading = uiState.isLoading
            )
        } else {
            CartScreen(
                cart = uiState.cart,
                onClickDelete = { product->
                    viewModel.onDeleteProduct(product)
                }
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
}

@Composable
fun CartScreen(
    cart: Flow<List<CartProduct>>,
    onClickDelete: (CartProduct) -> Unit,
) {
    val cartItems by cart.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "My Orders",
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, top = 10.dp),
            fontSize = 30.sp,
            fontFamily = Merri,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        DisplayProducts(
            cartsItem = cartItems,
            onClickDelete = onClickDelete
        )
    }
}

@Composable
fun DisplayProducts(
    cartsItem: List<CartProduct>,
    onClickDelete: (CartProduct) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 10.dp)
    ) {
        items(cartsItem) { cartItem ->
            CartItem(
                cart = cartItem,
                onClickDelete = { onClickDelete(cartItem) }
            )
        }
    }


}

@Composable
@Preview(showSystemUi = true)
fun PreviewProfileScreen() {
    val sampleCartProducts = listOf(
        CartProduct(
            id = 1,
            title = "Leather Tangerine Coat",
            price = 257.85,
            quantity = 1,
            thumbnail = "https://example.com/image1.jpg",
            total = 257.85,
            discountPercentage = 10.0,
            discountedTotal = 232.07
        ),
        CartProduct(
            id = 2,
            title = "Blue Denim Jacket",
            price = 150.00,
            quantity = 2,
            thumbnail = "https://example.com/image2.jpg",
            total = 300.00,
            discountPercentage = 5.0,
            discountedTotal = 285.00
        )
    )

    CartScreen(
        cart = flowOf(sampleCartProducts),
        onClickDelete = {}
    )
}