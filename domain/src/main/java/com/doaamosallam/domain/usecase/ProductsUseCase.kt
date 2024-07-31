package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.products.ProductResponse
import com.doaamosallam.domain.repo.ProductsRepo
import javax.inject.Inject

class ProductsUseCase(
    private val productsRepo: ProductsRepo
) {
    suspend fun getAllProducts(): ProductResponse = productsRepo.getAllProducts()
}