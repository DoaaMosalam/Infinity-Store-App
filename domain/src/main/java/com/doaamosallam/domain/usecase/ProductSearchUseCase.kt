package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.products.ProductResponse
import com.doaamosallam.domain.repo.ProductSearchRepo

class ProductSearchUseCase(private val productSearchRepo: ProductSearchRepo) {
    suspend fun searchProducts(query: String): ProductResponse  = productSearchRepo.searchProducts(query)
}