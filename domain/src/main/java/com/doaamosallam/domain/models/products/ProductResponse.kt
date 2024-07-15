package com.doaamosallam.domain.models.products

data class ProductResponse(
    val products: List<Product>,
    val limit: Int,
    val skip: Int,
    val total: Int
)