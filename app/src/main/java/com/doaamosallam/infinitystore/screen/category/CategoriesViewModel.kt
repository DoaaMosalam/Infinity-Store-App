package com.doaamosallam.infinitystore.screen.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.usecase.CategoriesUseCase
import com.doaamosallam.infinitystore.screen.category.event.CategoriesEvent
import com.doaamosallam.infinitystore.screen.category.state.CategoriesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val categoriesUseCase: CategoriesUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoriesUiState())
    val viewState: StateFlow<CategoriesUiState> get() = _uiState

    init {
        onFetchCategories()
    }

    fun emitEvent(event: CategoriesEvent) {
        when (event) {
            is CategoriesEvent.OnFetchCategories -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    categories = event.categories,
                    success = true
                )

            }

            is CategoriesEvent.LoadingState -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = event.isLoading
                )
            }

            is CategoriesEvent.OnError -> {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = event.message,
                    success = false
                )
            }

        }
    }


    private fun onFetchCategories() = viewModelScope.launch {
        try {
            emitEvent(CategoriesEvent.LoadingState(true))

            val result = categoriesUseCase.getAllCategories()
            emitEvent(CategoriesEvent.OnFetchCategories(result))
            emitEvent(CategoriesEvent.LoadingState(false))

        } catch (e: Exception) {
            emitEvent(CategoriesEvent.LoadingState(false))
            emitEvent(CategoriesEvent.OnError(e.message ?: "An error occurred"))

        }

    }
}