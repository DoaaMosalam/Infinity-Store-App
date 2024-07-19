package com.doaamosallam.infinitystore.viewmodel.search_product

sealed class ProductSearchIntent {
    data class SearchProducts(val query: String) : ProductSearchIntent()
}