package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.categories.CategoriesItem
import com.doaamosallam.domain.models.categories.CategoriesResponse

interface CategoriesRepo {
    suspend fun getAllCategories(): List<CategoriesItem>

}