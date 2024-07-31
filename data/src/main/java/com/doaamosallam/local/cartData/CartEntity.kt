package com.doaamosallam.local.cartData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_product_table")
data class CartProductEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val thumbnail: String,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double
)

