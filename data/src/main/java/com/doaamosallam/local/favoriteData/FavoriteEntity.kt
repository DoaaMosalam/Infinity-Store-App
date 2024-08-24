package com.doaamosallam.local.favoriteData

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_Product_table")
data class FavoriteEntity (
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: Double,
    val thumbnail: String,
    val discountPercentage: Double,
    val discountedTotal: Double,
    val rating : Double
)