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
        cart.id.let { id ->
            cartDao.addProductToCart(
                CartProductEntity(
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
            )
        }
    }

    override suspend fun getProductFromCart(): List<CartProduct> {
        return withContext(Dispatchers.IO) {
            cartDao.getProductFromCart().map { productEntity ->
                CartProduct(
                    id = productEntity.id,
                    title = productEntity.title,
                    price = productEntity.price,
                    quantity = productEntity.quantity,
                    thumbnail = productEntity.thumbnail,
                    total = productEntity.total,
                    discountPercentage = productEntity.discountPercentage,
                    discountedTotal = productEntity.discountedTotal
                )

            }
        }
    }
}
//    override suspend fun addProductToCart(cart: Cart) {
//        withContext(Dispatchers.IO) {
//            cart.id.let {id->
//            cartDao.insertCart(CartEntity(
//                id = cart.id,
//                userId = cart.userId,
//                total = cart.total,
//                discountedTotal = cart.discountedTotal,
//                totalProducts = cart.totalProducts,
//                totalQuantity = cart.totalQuantity
//            ))
//            cart.products.forEach { product ->
//                cartDao.addProductToCart(
//                    CartProductEntity(
//                        id = product.id,
//                        cartId = cart.id!!,
//                        title = product.title,
//                        price = product.price,
//                        quantity = product.quantity,
//                        thumbnail = product.thumbnail,
//                        total = product.total,
//                        discountPercentage = product.discountPercentage,
//                        discountedTotal = product.discountedTotal
//                    )
//                )
//            }
//
//            }
//        }
//    }
//
//    override suspend fun getProductFromCart(): List<Cart> {
//        return withContext(Dispatchers.IO) {
//            cartDao.getAllCart().map { cartEntity ->
//                val products = cartDao.getProductFromCart(cartEntity.id!!).map { productEntity ->
//                    CartProduct(
//                        id = productEntity.id,
//                        title = productEntity.title,
//                        price = productEntity.price,
//                        quantity = productEntity.quantity,
//                        thumbnail = productEntity.thumbnail,
//                        total = productEntity.total,
//                        discountPercentage = productEntity.discountPercentage,
//                        discountedTotal = productEntity.discountedTotal
//                    )
//                }
//                Cart(
//                    id = cartEntity.id,
//                    userId = cartEntity.userId,
//                    products = products,
//                    total = cartEntity.total,
//                    discountedTotal = cartEntity.discountedTotal,
//                    totalProducts = cartEntity.totalProducts,
//                    totalQuantity = cartEntity.totalQuantity
//                )
//            }
//        }
//    }
//}


