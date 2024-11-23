package com.example.mvvm.DB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Products")
    suspend fun getAllProducts(): List<Product>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(products: List<Product>) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product:Product) : Long

    @Delete
    suspend fun delete(product: Product): Int
}