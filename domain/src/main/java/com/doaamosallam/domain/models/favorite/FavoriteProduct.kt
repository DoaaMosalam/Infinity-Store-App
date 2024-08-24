package com.doaamosallam.domain.models.favorite

data class FavoriteProduct(
    val id: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val discountPercentage: Double,
    val discountedTotal: Double,
    val rating: Double,
)
