package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.cart.CartProduct
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing cart operations.
 */
interface CartRepository {

    suspend fun addProductToCart(cart: CartProduct)

    suspend fun getProductFromCart(): Flow<List<CartProduct>>
}