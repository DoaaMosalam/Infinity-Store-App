package com.doaamosallam.domain.models.favorite

import com.doaamosallam.domain.models.cart.CartProduct

data class Favorite(
    val id: Int?,
    val userId: Int,
    val products: List<FavoriteProduct>,
    val total: Double,
    val discountedTotal: Double,
    val totalProducts: Int,
    val totalQuantity: Int,
)
