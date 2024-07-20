package com.doaamosallam.repo

import com.doaamosallam.data.local.CartData.CartDAO
import com.doaamosallam.data.local.CartData.CartProductEntity
import com.doaamosallam.domain.models.cart.Cart
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.domain.repo.CartRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepoImp(private val cartDao: CartDAO): CartRepo {
    override suspend fun addProductToCart(cart: CartProduct) {
        val cartEntities = CartProductEntity(
            id = cart.id,
            cartId = cart.id,
            title = cart.title,
            price = cart.price,
            quantity = cart.quantity,
            thumbnail = cart.thumbnail,
            total = cart.total,
            discountPercentage = cart.discountPercentage,
            discountedTotal = cart.discountedTotal
        )
        cartDao.addProductToCart(cartEntities)

    }

    override suspend fun getProductFromCart(): List<CartProduct> {
        return cartDao.getProductFromCart().map { entiries->
            CartProduct(
                id = entiries.id,
                title = entiries.title,
                price = entiries.price,
                quantity = entiries.quantity,
                thumbnail = entiries.thumbnail,
                total = entiries.total,
                discountPercentage = entiries.discountPercentage,
                discountedTotal = entiries.discountedTotal
            )
        }
    }
    //    override suspend fun addProductToCart(cart: CartProduct) {
//        cart.id.let { id ->
//            cartDao.addProductToCart(
//                CartProductEntity(
//                id = cart.id,
//                cartId = cart.id,
//                title = cart.title,
//                price = cart.price,
//                quantity = cart.quantity,
//                thumbnail = cart.thumbnail,
//                total = cart.total,
//                discountPercentage = cart.discountPercentage,
//                discountedTotal = cart.discountedTotal
//                )
//            )
//        }
//    }
//
//    override suspend fun getProductFromCart(): List<CartProduct> {
//        return withContext(Dispatchers.IO) {
//            cartDao.getProductFromCart().map { productEntity ->
//                CartProduct(
//                    id = productEntity.id,
//                    title = productEntity.title,
//                    price = productEntity.price,
//                    quantity = productEntity.quantity,
//                    thumbnail = productEntity.thumbnail,
//                    total = productEntity.total,
//                    discountPercentage = productEntity.discountPercentage,
//                    discountedTotal = productEntity.discountedTotal
//                )
//
//            }
//        }
//    }

}



