package com.doaamosallam.infinitystore.viewmodel.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.usecase.ProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productsUseCase: ProductsUseCase
) : ViewModel() {
    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Loading)
    val viewState: StateFlow<HomeViewState> get() = _viewState.asStateFlow()

    init {
        fetchProducts()
    }

    // process
    fun handleIntent(event: HomeIntent) {
        when (event) {
            is HomeIntent.GetProducts -> fetchProducts()
        }
    }

    private fun fetchProducts() = viewModelScope.launch {
        Log.d("HomeViewModel", "Fetching products from API...")
        _viewState.value = HomeViewState.Loading
        try {
            val result = productsUseCase.getAllProducts()
            Log.d("HomeViewModel", "Products fetched successfully: ${result.products}")
            _viewState.value = HomeViewState.Success(result.products)

        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error fetching products", e)
            _viewState.value = HomeViewState.Error(e.message ?: "An error occurred")

        }
    }
}