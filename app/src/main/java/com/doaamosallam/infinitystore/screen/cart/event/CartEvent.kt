package com.doaamosallam.infinitystore.screen.cart.event

import com.doaamosallam.domain.models.cart.CartProduct
import kotlinx.coroutines.flow.Flow

sealed class CartEvent {
    data class GetAllProduct(val products: Flow<List<CartProduct>>) : CartEvent()
    data class OnDeleteProduct(val product: CartProduct) : CartEvent()
    data class LoadingState(val isLoading: Boolean) : CartEvent()
    data class OnError(val message: String) : CartEvent()

}