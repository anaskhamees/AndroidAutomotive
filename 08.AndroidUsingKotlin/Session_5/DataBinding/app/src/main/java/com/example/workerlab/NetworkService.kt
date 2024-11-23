package com.example.workerlab

import retrofit2.Call
import retrofit2.http.GET

interface NetworkService {
        @GET("products")
        fun getProducts(): Call<ProductsResponse>
}