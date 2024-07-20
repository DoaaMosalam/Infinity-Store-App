package com.doaamosallam.domain.models.cart

data class CartProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val thumbnail: String,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double
)