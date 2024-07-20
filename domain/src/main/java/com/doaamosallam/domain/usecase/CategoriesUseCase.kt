package com.doaamosallam.domain.usecase

import com.doaamosallam.domain.models.categories.CategoriesItem
import com.doaamosallam.domain.repo.CategoriesRepo

class CategoriesUseCase(private val categoriesRepo: CategoriesRepo) {
    suspend fun getAllCategories():List<CategoriesItem> = categoriesRepo.getAllCategories()

}