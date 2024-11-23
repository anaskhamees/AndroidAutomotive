package com.example.lab1

import java.io.Serializable

class Product (var name:String, var img:Int): Serializable
object RecyclerRepo{
    fun getProducts()= listOf<Product>(
        Product("Tomato",R.drawable.tomato),
        Product("Basil",R.drawable.basil),
        Product("Beetroot",R.drawable.beetroot),
        Product("Grapes",R.drawable.grapes),
        Product("Watermelon",R.drawable.watermelon),
        Product("Broccoli",R.drawable.broccoli)
    )
}