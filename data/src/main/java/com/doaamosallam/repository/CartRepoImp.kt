package com.doaamosallam.repository

import com.doaamosallam.local.cartData.CartDAO
import com.doaamosallam.mapper.mapToDB
import com.doaamosallam.mapper.mapToUi
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.repo.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CartRepoImp(private val cartDao: CartDAO) : CartRepository {

    override suspend fun addProductToCart(cart: CartProduct) {
        cartDao.addProductToCart(cart.mapToDB())
    }

    override suspend fun getProductFromCart(): Flow<List<CartProduct>> =
        cartDao.getProductFromCart().map { cartProductEntities ->
            cartProductEntities.map { it.mapToUi() }
        }
}



