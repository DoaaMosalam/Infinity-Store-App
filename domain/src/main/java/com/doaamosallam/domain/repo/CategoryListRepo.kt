package com.doaamosallam.domain.repo

import com.doaamosallam.domain.models.categories.CategoryList

interface CategoryListRepo {
    suspend fun getAllCategoryList():List<CategoryList>
}