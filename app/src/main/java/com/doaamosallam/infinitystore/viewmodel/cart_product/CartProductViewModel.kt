package com.doaamosallam.infinitystore.viewmodel.cart_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.cart.Cart
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.usecase.CartUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartUseCase: CartUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<CartViewState>(CartViewState.Idle)
    val viewState: StateFlow<CartViewState> get() = _viewState

    fun handleIntent(intent: CartIntent) {
        when (intent) {
            is CartIntent.AddToCart -> addToCart(intent.cart)
            is CartIntent.GetCart -> getCart()
        }
    }

    private fun addToCart(cart: CartProduct) = viewModelScope.launch {
        _viewState.value = CartViewState.Loading
        try {
            cartUseCase.addProductToCart(cart)
            getCart() // Refresh the cart to show the updated state
        } catch (e: Exception) {
            _viewState.value = CartViewState.Error(e.message ?: "An error occurred")
        }
    }

    private fun getCart() = viewModelScope.launch {
        _viewState.value = CartViewState.Loading
        try {
            val result = cartUseCase.getProductFromCart()
            _viewState.value = CartViewState.Success(result)
        } catch (e: Exception) {
            _viewState.value = CartViewState.Error(e.message ?: "An error occurred")
        }
    }
}


