package com.doaamosallam.infinitystore.screen.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.infinitystore.R
import com.doaamosallam.infinitystore.compose.CartItem
import com.doaamosallam.infinitystore.compose.CheckOut_PayNow
import com.doaamosallam.infinitystore.compose.DisplayTotalsItems_Price
import com.doaamosallam.infinitystore.compose.FullScreenLoading
import com.doaamosallam.infinitystore.compose.TopBarScreen
import com.doaamosallam.infinitystore.navigation.Screen
import com.doaamosallam.infinitystore.ui.theme.Merri
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

//state hoisting
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartContainer(navController: NavController) {
    val viewModel: CartViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

//    Scaffold(
//        bottomBar = { BottomNavigationBar(navController = navController) }
//    ) {

    if (uiState.isLoading) {
        FullScreenLoading(
            modifier = Modifier.fillMaxSize(),
            isLoading = uiState.isLoading
        )
    } else {
        CartScreen(
            cart = uiState.cart,
            onClickDelete = { product ->
                viewModel.onDeleteProduct(product)
            },
            itemsTotal = uiState.totalItems,
            priceTotal = uiState.totalPrice,
            onClickBack = { navController.popBackStack() },
            onClickCheckOut = { navController.navigate(Screen.PaymentScreen.route) }

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
//}

@Composable
fun CartScreen(
    cart: Flow<List<CartProduct>>,
    onClickDelete: (CartProduct) -> Unit,
    itemsTotal: Int,
    priceTotal: Double,
    onClickBack: () -> Unit,
    onClickCheckOut: () -> Unit
) {
    val cartItems by cart.collectAsState(initial = emptyList())
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomEnd,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            TopBarScreen(
                modifier = Modifier.padding(top = 10.dp, start = 16.dp),
                onClickBack = onClickBack,
                text = "My Orders",
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = Merri,
                color = Color.DarkGray
            )

            Spacer(modifier = Modifier.height(16.dp))

            DisplayProducts(
                cartsItem = cartItems,
                onClickDelete = onClickDelete,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .border(1.dp, Color.LightGray, RoundedCornerShape(10.dp))
                .padding(10.dp)
        ) {
            // Display total items and total price
            DisplayTotalsItems_Price(itemsTotal, priceTotal)
            Spacer(modifier = Modifier.height(8.dp))
            // PayNow button
            CheckOut_PayNow(
                onClick = { onClickCheckOut() },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        colorResource(id = R.color.primary_color),
                        shape = RoundedCornerShape(20.dp)
                    ),
                buttonText = stringResource(R.string.checkout),
                buttonColor = colorResource(id = R.color.primary_color),
                textColor = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 18,
                fontFamily = Merri,
                color = Color.White
            )
        }
    }
}

@Composable
fun DisplayProducts(
    cartsItem: List<CartProduct>,
    onClickDelete: (CartProduct) -> Unit,

    ) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(cartsItem) { cartItem ->
            CartItem(
                cart = cartItem,
                onClickDelete = { onClickDelete(cartItem) },
            )

        }
    }
}

@Composable
@Preview(showBackground = true)
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
        onClickDelete = {},
        itemsTotal = 2,
        priceTotal = 500.00,
        onClickBack = {},
        onClickCheckOut = {}
    )
}