package com.doaamosallam.remote

import com.doaamosallam.domain.models.categories.CategoriesItem
import com.doaamosallam.domain.models.products.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * An interface defining the API endpoints for interacting with a product catalog.
 */
interface APIService {

    @GET("products")
    suspend fun getAllProducts(): ProductResponse

    @GET("products/category-list")
    suspend fun getAllCategoryList(): List<String>

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): ProductResponse

    @GET("products/categories")
    suspend fun getAllCategories(): List<CategoriesItem>
}
