package com.doaamosallam.infinitystore.viewmodel.categories

import com.doaamosallam.domain.models.categories.CategoriesItem

sealed class CategoriesViewState {
    data object Idle : CategoriesViewState()
    data object Loading : CategoriesViewState()
    data class Success(val data: List<CategoriesItem>) : CategoriesViewState()
    data class Error(val message: String) : CategoriesViewState()
}