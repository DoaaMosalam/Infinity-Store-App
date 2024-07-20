package com.doaamosallam.domain.models.cart

data class CartResponse(
    val carts: List<Cart>,
    val limit: Int,
    val skip: Int,
    val total: Int
)