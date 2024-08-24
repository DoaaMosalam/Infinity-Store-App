package com.doaamosallam.mapper

import com.doaamosallam.domain.models.favorite.Favorite
import com.doaamosallam.domain.models.favorite.FavoriteProduct
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.local.favoriteData.FavoriteEntity


fun FavoriteProduct.mapToFavoriteDB(): FavoriteEntity{
    return FavoriteEntity(
        id = id,
        title = title,
        price = price,
        thumbnail = thumbnail,
        discountPercentage = discountPercentage,
        discountedTotal = discountedTotal,
        rating = rating

    )
}

fun FavoriteEntity.mapToFavoriteUi(): FavoriteProduct {
    return FavoriteProduct(
        id = id,
        title = title,
        price = price,
        thumbnail = thumbnail,
        discountPercentage = discountPercentage,
        discountedTotal = discountedTotal,
       rating = rating
    )
}

fun Product.mapToFavorite(): FavoriteProduct{
    return FavoriteProduct(
        id = id,
        title = title,
        price = price,
        thumbnail = thumbnail,
        discountPercentage = 0.0,
        discountedTotal = price,
        rating = rating
    )
}