package com.doaamosallam.infinitystore.viewmodel.cart_product

import com.doaamosallam.domain.models.cart.Cart
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.models.products.Product

sealed class CartIntent {
    data class AddToCart(val cart: CartProduct) : CartIntent()
    data object GetCart : CartIntent()
}