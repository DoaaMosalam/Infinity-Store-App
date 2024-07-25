package com.doaamosallam.infinitystore.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.usecase.CartUseCase
import com.doaamosallam.infinitystore.screen.cart.event.CartEvent
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

    private val _uiState = MutableStateFlow(CartUiState())
    val uiState: StateFlow<CartUiState> get() = _uiState

    init {
        getCart()
    }


    private fun emitEvent(event: CartEvent) {
        when (event) {
            is CartEvent.GetAllProduct -> {
                viewModelScope.launch {
                    event.products.collect { cartItems ->
                        // Calculating total items and total price
                        val totalItems = cartItems.sumOf { it.quantity }
                        val totalPrice = cartItems.sumOf { it.price * it.quantity }
                        val orderId = cartItems.map { it.id }.joinToString(",")
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            cart = event.products,
                            success = true,
                            totalItems = totalItems,
                            totalPrice = totalPrice,
                            orderId = orderId
                        )
                    }
                }
            }

            is CartEvent.LoadingState -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = event.isLoading
                )
            }

            is CartEvent.OnError -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = event.message,
                    success = false
                )
            }

            is CartEvent.OnDeleteProduct -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    success = true
                )
            }
        }
    }

    private fun getCart() = viewModelScope.launch {
        try {
            emitEvent(CartEvent.LoadingState(true))
            val result = cartUseCase.getProductFromCart()
            emitEvent(CartEvent.GetAllProduct(result))
            emitEvent(CartEvent.LoadingState(false))
        } catch (e: Exception) {
            emitEvent(CartEvent.OnError(e.message ?: "An error occurred"))
        }
    }

    fun onDeleteProduct(product: CartProduct) = viewModelScope.launch {
        try {
            emitEvent(CartEvent.LoadingState(false))
            cartUseCase.deleteProductFromCart(product)
            emitEvent(CartEvent.OnDeleteProduct(product))
            getCart()
        } catch (e: Exception) {
            emitEvent(CartEvent.OnError(e.message ?: "An error occurred"))
        }
    }
}



