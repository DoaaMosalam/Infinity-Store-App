package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.cart.Cart
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.models.cart.CartResponse
import kotlinx.coroutines.flow.Flow

interface CartRepo {
    // insert product to cart
    suspend fun addProductToCart(cart: CartProduct)
    // get product from cart
    suspend fun getProductFromCart():List<CartProduct>
//            Flow<List<Cart>>
}