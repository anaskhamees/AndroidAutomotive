package com.example.workerlab

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductDao {
    @Query("SELECT * FROM product_table")
    fun getAllProducts(): List<Product>

    @Insert
    suspend fun insertAll(products: List<Product>)

    @Query("DELETE FROM product_table")
    suspend fun deleteAll()
}