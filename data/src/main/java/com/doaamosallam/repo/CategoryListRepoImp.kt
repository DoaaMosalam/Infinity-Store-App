package com.doaamosallam.repo

import com.doaamosallam.data.remote.APIService
import com.doaamosallam.domain.models.categories.CategoryList
import com.doaamosallam.domain.repo.CategoryListRepo

class CategoryListRepoImp  (private val apiService: APIService) : CategoryListRepo{
    override suspend fun getAllCategoryList(): List<CategoryList> = apiService.getAllCategoryList().map {
        CategoryList(it)
    }
}