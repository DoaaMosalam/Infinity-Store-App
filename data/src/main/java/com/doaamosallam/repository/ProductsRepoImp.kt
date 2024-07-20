package com.doaamosallam.repository

import com.doaamosallam.domain.models.products.ProductResponse
import com.doaamosallam.domain.repo.ProductsRepo
import com.doaamosallam.remote.APIService

class ProductsRepoImp(private val apiService: APIService): ProductsRepo {
    override suspend fun getAllProducts(): ProductResponse = apiService.getAllProducts()
}