package com.doaamosallam.infinitystore.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.favorite.FavoriteProduct
import com.doaamosallam.domain.usecase.FavoriteUseCase
import com.doaamosallam.infinitystore.screen.favorite.event.FavoriteEvent
import com.doaamosallam.infinitystore.screen.favorite.state.FavoriteUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteUseCase: FavoriteUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteUiState())
    val uiState: StateFlow<FavoriteUiState> get() = _uiState

    init {
        getProductFavorite()
    }

    private fun emitEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.OnGetProductFavorite -> {
                viewModelScope.launch {
                    event.products.collect {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            favorite = event.products,
                            success = true
                        )
                    }
                }
            }


            is FavoriteEvent.IsLoading -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = event.loading
                )
            }

            is FavoriteEvent.OnRemoveProductFavorite -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    success = true
                )
            }

            is FavoriteEvent.OnError -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = event.message,
                    success = false
                )
            }

            is FavoriteEvent.OnSuccess -> {
                _uiState.value = _uiState.value.copy(
                    success = event.isSuccess
                )
            }
        }
    }

    private fun getProductFavorite() = viewModelScope.launch {

        try {
            emitEvent(FavoriteEvent.IsLoading(true))
            val result = favoriteUseCase.getProductFromFavorite()
            emitEvent(FavoriteEvent.OnGetProductFavorite(result))
            emitEvent(FavoriteEvent.IsLoading(false))
        } catch (e: Exception) {
            emitEvent(FavoriteEvent.OnError(e.message ?: "An error occurred"))
        }
    }

    fun onRemoveProductFavorite(product: FavoriteProduct) = viewModelScope.launch {
        try {
            emitEvent(FavoriteEvent.IsLoading(true))
            favoriteUseCase.deleteProductFromFavorite(product)
            emitEvent(FavoriteEvent.OnRemoveProductFavorite(product))
            getProductFavorite()
        } catch (e: Exception) {
            emitEvent(FavoriteEvent.OnError(e.message ?: "An error occurred"))
        }

    }

}