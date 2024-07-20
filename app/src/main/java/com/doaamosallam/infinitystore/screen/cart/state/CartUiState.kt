package com.doaamosallam.infinitystore.screen.cart.state

import com.doaamosallam.domain.models.cart.CartProduct
import kotlinx.coroutines.flow.Flow

sealed class CartUiState {
    data object Idle : CartUiState()
    data object Loading: CartUiState()
    data class Success(val cart: Flow<List<CartProduct>>): CartUiState()
    data class Error(val message: String): CartUiState()
}