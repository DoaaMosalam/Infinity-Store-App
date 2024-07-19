package com.doaamosallam.data.remote

import com.doaamosallam.domain.models.categories.CategoryList
import com.doaamosallam.domain.models.products.Product
import com.doaamosallam.domain.models.products.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

/*
* This class interface request API */
interface APIService {
    // get all products
    @GET("products")
    suspend fun getAllProducts():ProductResponse
    // get all name CategoryList
    @GET("products/category-list")
    suspend fun getAllCategoryList(): List<String>
    // search products
    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): ProductResponse
}
