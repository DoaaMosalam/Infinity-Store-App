package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.products.ProductResponse

interface ProductSearchRepo {
    suspend fun searchProducts(query:String): ProductResponse
}