package com.doaamosallam.infinitystore.screen.cart.state

import com.doaamosallam.domain.models.cart.CartProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class CartUiState(
    val cart: Flow<List<CartProduct>> = emptyFlow(),
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = "",
    val totalItems: Int = 0,
    val totalPrice: Double = 0.0,
    val orderId: String = ""
)