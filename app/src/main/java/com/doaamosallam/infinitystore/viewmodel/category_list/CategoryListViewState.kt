package com.doaamosallam.infinitystore.viewmodel.category_list

import com.doaamosallam.domain.models.categories.CategoryList

sealed class CategoryListViewState {
    data object Idle: CategoryListViewState()
    data object Loading: CategoryListViewState()
    data class Success(val data: List<CategoryList>) : CategoryListViewState()
    data class Error(val message: String) : CategoryListViewState()
}