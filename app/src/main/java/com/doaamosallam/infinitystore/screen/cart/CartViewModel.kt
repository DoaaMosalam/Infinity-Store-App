package com.doaamosallam.infinitystore.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.usecase.CartUseCase
import com.doaamosallam.infinitystore.screen.cart.state.CartUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase,
) : ViewModel() {

    private val _viewState = MutableStateFlow<CartUiState>(CartUiState.Idle)
    val viewState: StateFlow<CartUiState> get() = _viewState


    private fun addToCart(cart: CartProduct) = viewModelScope.launch {
        _viewState.value = CartUiState.Loading
        try {
            cartUseCase.addProductToCart(cart)
            getCart() // Refresh the cart to show the updated state
        } catch (e: Exception) {
            _viewState.value = CartUiState.Error(e.message ?: "An error occurred")
        }
    }

    private fun getCart() = viewModelScope.launch {
        _viewState.value = CartUiState.Loading
        try {
            val result = cartUseCase.getProductFromCart()
            _viewState.value = CartUiState.Success(result)
        } catch (e: Exception) {
            _viewState.value = CartUiState.Error(e.message ?: "An error occurred")
        }
    }
}


