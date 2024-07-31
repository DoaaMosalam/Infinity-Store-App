package com.doaamosallam.infinitystore.screen.category.event

import com.doaamosallam.domain.models.categories.CategoriesItem

sealed class CategoriesEvent {
    data class OnFetchCategories(val categories: List<CategoriesItem>) : CategoriesEvent()
    data class LoadingState(val isLoading: Boolean) : CategoriesEvent()
    data class OnError(val message: String) : CategoriesEvent()
}