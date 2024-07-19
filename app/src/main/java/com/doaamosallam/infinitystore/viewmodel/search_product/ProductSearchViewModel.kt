package com.doaamosallam.infinitystore.viewmodel.search_product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.doaamosallam.domain.usecase.ProductSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductSearchViewModel @Inject constructor(
    private val productSearchUseCase: ProductSearchUseCase
):ViewModel() {
    private val _viewState = MutableStateFlow<ProductSearchViewState>(ProductSearchViewState.Loading)
    val viewState : StateFlow<ProductSearchViewState> get() = _viewState.asStateFlow()

    fun handleIntent(intent: ProductSearchIntent) {
        when(intent){
            is ProductSearchIntent.SearchProducts -> fetchProducts(intent.query)
        }
    }

    private fun fetchProducts(query: String) = viewModelScope.launch {
        _viewState.value = ProductSearchViewState.Loading
        try{
            val result = productSearchUseCase.searchProducts(query)
            _viewState.value = ProductSearchViewState.Success(result.products)

        }catch (e:Exception){
            _viewState.value = ProductSearchViewState.Error(e.message ?: "An error occurred")
        }
    }
}