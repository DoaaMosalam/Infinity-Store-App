package com.doaamosallam.remote

import com.doaamosallam.domain.models.products.ProductResponse
import retrofit2.http.GET

/*
* This class interface request API */
interface APIService {
    // get all products
    @GET("products")
    suspend fun getAllProducts():ProductResponse
}