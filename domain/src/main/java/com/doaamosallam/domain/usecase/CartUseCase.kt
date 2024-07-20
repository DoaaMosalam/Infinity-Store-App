package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.repo.CartRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

/**
 * Use case class for interacting with the cart repository.
 * @property cartRepo The cart repository to interact with.
 */
@ViewModelScoped
class CartUseCase @Inject constructor(
    private val cartRepo: CartRepository,
) {

    suspend fun addProductToCart(cart: CartProduct) = cartRepo.addProductToCart(cart)

    suspend fun getProductFromCart() = cartRepo.getProductFromCart()
}