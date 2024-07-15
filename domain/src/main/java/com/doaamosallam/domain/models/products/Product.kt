package com.doaamosallam.domain.models.products

data class Product(
    val id: Int,
    val title: String,
    val brand: String,
    val category: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val images: List<String>,
    val rating: Double,
//    val reviews: List<Review>,
    val thumbnail: String,
    val stock: Int,
)