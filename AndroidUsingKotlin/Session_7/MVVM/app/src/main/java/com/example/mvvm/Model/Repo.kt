package com.example.mvvm.Model

import com.example.mvvm.Product
import com.example.mvvm.DB.ProductLocalDataSource
import com.example.mvvm.Network.RetrofitHelper

class Repo(private val localSource : ProductLocalDataSource) {

    companion object {
        @Volatile
        private var INSTANCE: Repo? = null

        fun getInstance(localDataSource: ProductLocalDataSource): Repo? {
            return INSTANCE ?: synchronized(this) {
                INSTANCE = Repo(localDataSource)
                INSTANCE
            }
        }
    }

    suspend fun getProducts(): List<Product> {
        val serviceObj = RetrofitHelper.service
        val result = serviceObj.getProducts()
        val productList = result.products
        return productList
    }

    suspend fun insertProduct(product: Product) {
            localSource.insertProduct(product)
    }

    suspend fun getAllFav(): List<Product>{
        return localSource.getAllFav()
    }

    suspend fun delete(product: Product) {
        localSource.delete(product)
    }
}