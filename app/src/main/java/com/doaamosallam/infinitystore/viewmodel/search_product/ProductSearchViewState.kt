package com.doaamosallam.infinitystore.viewmodel.search_product

import com.doaamosallam.domain.models.products.Product

sealed class ProductSearchViewState {
    object Idle:ProductSearchViewState()
    object Loading:ProductSearchViewState()
    data class Success(val products:List<Product>):ProductSearchViewState()
    data class Error(val message:String):ProductSearchViewState()

}