package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.favorite.FavoriteProduct
import com.doaamosallam.domain.repo.FavoriteRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
/**
 *  Use case class for interacting with the favorite repository.
 *  @property  favoriteRepo The favorite repository to interact with.
 * */
@ViewModelScoped
class FavoriteUseCase @Inject constructor (
    private val favoriteRepo: FavoriteRepository
) {
    suspend fun addProductToFavorite(favorite:FavoriteProduct) = favoriteRepo.addProductToFavorite(favorite)

    suspend fun getProductFromFavorite()= favoriteRepo.getProductFromFavorite()

    suspend fun deleteProductFromFavorite(favorite: FavoriteProduct) = favoriteRepo.deleteProductFromFavorite(favorite)
}