package com.example.workerlab

import retrofit2.Response
import retrofit2.http.GET

interface NetworkService {
        @GET("products")
        suspend fun getProducts(): Response<ProductsResponse>  // Return Response<ProductsResponse>
}
