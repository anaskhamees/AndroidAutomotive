package com.example.mvvm

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "products")
data class Product(@PrimaryKey val id: Int,
                   val title: String,
                   val description: String,
                   val category: String,
                   val price: String,
                   val rating: Double,
                   val brand: String,
                   val thumbnail: String )
