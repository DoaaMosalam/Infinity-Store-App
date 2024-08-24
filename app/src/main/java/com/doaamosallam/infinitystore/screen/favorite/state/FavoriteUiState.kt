package com.doaamosallam.infinitystore.screen.favorite.state

import com.doaamosallam.domain.models.favorite.FavoriteProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class FavoriteUiState(
    val favorite: Flow<List<FavoriteProduct>> = emptyFlow(),
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = "",
)