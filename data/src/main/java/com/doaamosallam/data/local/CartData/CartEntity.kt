package com.doaamosallam.data.local.CartData

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.doaamosallam.domain.models.cart.CartProduct

//@Entity(tableName = "cart_table")
//data class CartEntity(
//    @PrimaryKey(autoGenerate = true)
//    val id: Int?,
//    val userId: Int,
//    val total: Double,
//    val discountedTotal: Double,
//    val totalProducts: Int,
//    val totalQuantity: Int,
//)

@Entity(tableName = "cart_product_table")
data class CartProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val cartId: Int,
    val title: String,
    val price: Double,
    val quantity: Int,
    val thumbnail: String,
    val total: Double,
    val discountPercentage: Double,
    val discountedTotal: Double
)

