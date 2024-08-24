package com.doaamosallam.domain.repo


import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.models.favorite.FavoriteProduct
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

   suspend fun  addProductToFavorite(favorite: FavoriteProduct)

   suspend fun getProductFromFavorite():Flow<List<FavoriteProduct>>

   suspend fun deleteProductFromFavorite(favorite: FavoriteProduct)

}
