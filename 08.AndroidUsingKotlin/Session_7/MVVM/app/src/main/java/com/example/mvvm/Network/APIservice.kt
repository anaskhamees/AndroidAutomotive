package com.example.mvvm.Network

import com.example.mvvm.ProductResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface APIservice {

    @GET("products")
    suspend fun getProducts() : ProductResponse
}



object RetrofitHelper{
    private const val BASE_URL = "https://dummyjson.com/"
    private val retrofitInstance = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val service = retrofitInstance.create(APIservice::class.java)

}
