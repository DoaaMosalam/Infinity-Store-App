package com.doaamosallam.infinitystore.viewmodel.cart_product

import com.doaamosallam.data.local.CartData.CartProductEntity
import com.doaamosallam.domain.models.cart.Cart
import com.doaamosallam.domain.models.cart.CartProduct

sealed class CartViewState {
    data object Idle : CartViewState()
    data object Loading:CartViewState()
    data class Success(val cart: List<CartProduct>):CartViewState()
    data class Error(val message: String):CartViewState()
}