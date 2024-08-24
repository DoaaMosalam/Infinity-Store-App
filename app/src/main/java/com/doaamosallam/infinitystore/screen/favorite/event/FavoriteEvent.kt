package com.doaamosallam.infinitystore.screen.favorite.event

import com.doaamosallam.domain.models.favorite.FavoriteProduct
import kotlinx.coroutines.flow.Flow

sealed class FavoriteEvent {
    data class OnGetProductFavorite(val products: Flow<List<FavoriteProduct>>) : FavoriteEvent()
    data class OnRemoveProductFavorite(val product: FavoriteProduct) : FavoriteEvent()
    data class IsLoading(val loading: Boolean) : FavoriteEvent()
    data class OnError(val message: String) : FavoriteEvent()
    data class OnSuccess(val isSuccess: Boolean) : FavoriteEvent()
}