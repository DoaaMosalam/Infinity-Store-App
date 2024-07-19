package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.categories.CategoryList
import com.doaamosallam.domain.repo.CategoryListRepo

class CategoryListUseCase(   private val categoryListRepo: CategoryListRepo
) {
    suspend fun getAllCategoryList(): List<CategoryList> {
        return categoryListRepo.getAllCategoryList()
    }
}