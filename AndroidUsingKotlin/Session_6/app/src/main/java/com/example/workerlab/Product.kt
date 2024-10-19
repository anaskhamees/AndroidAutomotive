package com.example.workerlab

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product_table")
data class Product(@PrimaryKey val id: Int, val title: String, val price: Double, val thumbnail: String)