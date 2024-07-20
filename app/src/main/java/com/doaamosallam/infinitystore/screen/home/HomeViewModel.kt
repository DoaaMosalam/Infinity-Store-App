package com.doaamosallam.infinitystore.screen.home

import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.models.cart.CartProduct
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.domain.usecase.CartUseCase
import com.doaamosallam.domain.usecase.CategoryListUseCase
import com.doaamosallam.domain.usecase.ProductSearchUseCase
import com.doaamosallam.domain.usecase.ProductsUseCase
import com.doaamosallam.infinitystore.screen.home.event.HomeEvent
import com.doaamosallam.infinitystore.screen.home.state.HomeUiState
import com.doaamosallam.infinitystore.util.BaseViewModel
import com.doaamosallam.mapper.mapToCart
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase,
    private val cartUseCase: CartUseCase,
    private val categoryListUseCase: CategoryListUseCase,
    private val productSearchUseCase: ProductSearchUseCase,
) : BaseViewModel<HomeUiState, HomeEvent>(HomeUiState()) {

    override fun reduce(oldState: HomeUiState, sideEffect: HomeEvent) {
        when (sideEffect) {
            is HomeEvent.OnAddToCart -> {
                createNewState(
                    oldState.copy(
                        success = true
                    )
                )

                addToCart(sideEffect.product)
            }

            is HomeEvent.OnFetchProducts -> {
                createNewState(
                    oldState.copy(
                        isLoading = false,
                        products = sideEffect.products,
                        success = true
                    )
                )

            }

            is HomeEvent.OnFetchCategories -> {
                createNewState(
                    oldState.copy(
                        isLoading = false,
                        categories = sideEffect.categories,
                        success = true
                    )
                )
            }

            is HomeEvent.OnSearchQueryChange -> {
                createNewState(
                    oldState.copy(
                        isLoading = false,
                        search = sideEffect.query,
                        success = true
                    )
                )

                if (sideEffect.query.isNotEmpty()) {
                    onSearchChanges(sideEffect.query)
                }
            }

            is HomeEvent.OnError -> {
                createNewState(
                    oldState.copy(
                        isLoading = false,
                        error = sideEffect.message,
                        success = false
                    )
                )
            }

            is HomeEvent.LoadingState -> {
                createNewState(
                    oldState.copy(
                        isLoading = sideEffect.isLoading,
                    )
                )
            }
        }
    }

    init {
        onSearchChanges()
        fetchCategoryList()
    }

    private fun addToCart(cart: CartProduct) = viewModelScope.launch {
        try {
            emitEvent(HomeEvent.LoadingState(true))
            cartUseCase.addProductToCart(cart)
            emitEvent(HomeEvent.LoadingState(false))
        } catch (e: Exception) {
            emitEvent(HomeEvent.LoadingState(false))
            emitEvent(HomeEvent.OnError(e.message ?: "An error occurred"))
        }
    }

    private fun onSearchChanges() = viewModelScope.launch {
        try {
            emitEvent(HomeEvent.LoadingState(true))
            val result = productsUseCase.getAllProducts()
            emitEvent(HomeEvent.OnFetchProducts(result.products))
        } catch (e: Exception) {
            emitEvent(HomeEvent.OnError(e.message ?: "An error occurred"))
        }
    }

    private fun fetchCategoryList() = viewModelScope.launch {
        try {
            emitEvent(HomeEvent.LoadingState(true))
            val result = categoryListUseCase.getAllCategoryList()
            emitEvent(HomeEvent.OnFetchCategories(result))
        } catch (e: Exception) {
            emitEvent(HomeEvent.OnError(e.message ?: "An error occurred"))
        }
    }

    private fun onSearchChanges(query: String) = viewModelScope.launch {
        try {
            emitEvent(HomeEvent.LoadingState(true))
            val result = productSearchUseCase.searchProducts(query)
            emitEvent(HomeEvent.OnFetchProducts(result.products))

        } catch (e: Exception) {
            emitEvent(HomeEvent.OnError(e.message ?: "An error occurred"))
        }
    }

    fun onAddProductToCart(product: Product) {
        emitEvent(HomeEvent.OnAddToCart(product.mapToCart()))

    }

    fun onSearchEvent(query: String) {
        emitEvent(HomeEvent.OnSearchQueryChange(query))
    }
}