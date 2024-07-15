package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.products.ProductResponse

interface ProductsRepo {
    suspend fun getAllProducts():ProductResponse
}