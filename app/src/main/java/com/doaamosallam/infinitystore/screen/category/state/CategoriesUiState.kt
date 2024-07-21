package com.doaamosallam.infinitystore.screen.category.state

import com.doaamosallam.domain.models.categories.CategoriesItem

data class CategoriesUiState(
    val categories :List<CategoriesItem> = emptyList(),
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = ""
)