package com.doaamosallam.repo

import com.doaamosallam.data.remote.APIService
import com.doaamosallam.domain.models.categories.CategoriesItem
import com.doaamosallam.domain.repo.CategoriesRepo

class CategoriesRepoImp(private val apiService: APIService): CategoriesRepo {
    override suspend fun getAllCategories(): List<CategoriesItem> = apiService.getAllCategories()
}