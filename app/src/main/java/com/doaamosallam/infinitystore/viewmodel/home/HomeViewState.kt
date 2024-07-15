package com.doaamosallam.infinitystore.viewmodel.home

import com.doaamosallam.domain.models.products.Product

sealed class HomeViewState {
    object Idle:HomeViewState()
    object Loading:HomeViewState()
    data class Success(val products: List<Product>) : HomeViewState()
    data class Error(val message: String) : HomeViewState()

}