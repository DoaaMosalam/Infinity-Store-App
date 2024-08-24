package com.doaamosallam.repository

import android.util.Log
import com.doaamosallam.domain.models.favorite.FavoriteProduct
import com.doaamosallam.domain.repo.FavoriteRepository
import com.doaamosallam.local.favoriteData.FavoriteDAO
import com.doaamosallam.mapper.mapToFavoriteDB
import com.doaamosallam.mapper.mapToFavoriteUi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteRepoImp(private val favoriteDao: FavoriteDAO): FavoriteRepository {
    override suspend fun addProductToFavorite(favorite: FavoriteProduct){
        Log.d("FavoriteRepoImp", "Adding product to favorites: $favorite")
         favoriteDao.addProductToFavorite(favorite.mapToFavoriteDB())
    }



    override suspend fun getProductFromFavorite(): Flow<List<FavoriteProduct>> =
        favoriteDao.getProductFromFavorite().map { favoriteProductEntities ->
            favoriteProductEntities.map { it.mapToFavoriteUi() }
    }

    override suspend fun deleteProductFromFavorite(favorite: FavoriteProduct)
    = favoriteDao.deleteProductFromFavorite(favorite.mapToFavoriteDB())
}