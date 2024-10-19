package com.example.mvvm.DB

import com.example.mvvm.DB.ProductDao
import com.example.mvvm.Product

class ProductLocalDataSource(private val productDao:ProductDao) {

    suspend fun getAllFav() : List<Product>{
        return productDao.getAllProducts()
    }

    suspend fun insertProduct(product: Product) {
        productDao.insert(product)
    }

    suspend fun delete(product: Product){
        productDao.delete(product)
    }
}