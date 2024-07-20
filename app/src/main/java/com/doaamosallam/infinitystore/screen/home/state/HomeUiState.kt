package com.doaamosallam.infinitystore.screen.home.state

import com.doaamosallam.domain.models.categories.CategoryList
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.util.ScreenState

data class HomeUiState(
    val products: List<Product> = emptyList(),
    val categories: List<CategoryList> = emptyList(),
    val isLoading: Boolean = false,
    val success: Boolean = false,
    val error: String = "",
    val search: String = "",
): ScreenState