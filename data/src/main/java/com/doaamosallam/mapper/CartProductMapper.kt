package com.doaamosallam.mapper

import com.doaamosallam.local.cartData.CartProductEntity
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.models.products.Product


fun CartProduct.mapToDB(): CartProductEntity {
    return CartProductEntity(
        id = id,
        title = title,
        price = price,
        quantity = quantity,
        thumbnail = thumbnail,
        total = total,
        discountPercentage = discountPercentage,
        discountedTotal = discountedTotal
    )
}

fun CartProductEntity.mapToUi(): CartProduct {
    return CartProduct(
        id = id,
        title = title,
        price = price,
        quantity = quantity,
        thumbnail = thumbnail,
        total = total,
        discountPercentage = discountPercentage,
        discountedTotal = discountedTotal
    )
}

fun Product.mapToCart(): CartProduct {
    return CartProduct(
        id = id,
        title = title,
        price = price,
        quantity = 1,
        thumbnail = thumbnail,
        total = price,
        discountPercentage = 0.0,
        discountedTotal = price
    )
}




