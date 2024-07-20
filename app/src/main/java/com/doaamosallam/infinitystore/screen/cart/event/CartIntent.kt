package com.doaamosallam.infinitystore.screen.cart.event

import com.doaamosallam.domain.models.cart.CartProduct

sealed class CartIntent {
    data class AddToCart(val cart: CartProduct) : CartIntent()
    data object GetCart : CartIntent()
}