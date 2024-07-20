package com.doaamosallam.repository

import com.doaamosallam.remote.APIService
import com.doaamosallam.domain.models.products.ProductResponse
import com.doaamosallam.domain.repo.ProductSearchRepo

class ProductSearchRepoImp(private val apiService: APIService): ProductSearchRepo {
    override suspend fun searchProducts(query: String): ProductResponse = apiService.searchProducts(query)
}