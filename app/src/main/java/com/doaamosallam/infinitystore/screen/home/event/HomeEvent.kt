package com.doaamosallam.infinitystore.screen.home.event

import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.models.categories.CategoryList
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.infinitystore.util.ScreenEvent

/**
 * Represents possible events that can occur in the Home screen.
 */
sealed class HomeEvent : ScreenEvent {
    data class OnSearchQueryChange(val query: String) : HomeEvent()

    data class OnFetchProducts(val products: List<Product>) : HomeEvent()
    data class OnFetchCategories(val categories: List<CategoryList>) : HomeEvent()
    data class LoadingState(val isLoading: Boolean) : HomeEvent()
    data class OnError(val message: String) : HomeEvent()
    data class OnAddToCart(val product: CartProduct) : HomeEvent()
}
