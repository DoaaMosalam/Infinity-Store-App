package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.cart.Cart
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.repo.CartRepo

class CartUseCase(
    private val cartRepo: CartRepo
) {
   // insert product to cart
    suspend fun addProductToCart(cart: CartProduct) = cartRepo.addProductToCart(cart)
    // get product from cart
    suspend fun getProductFromCart():List<CartProduct> =cartRepo.getProductFromCart()
}