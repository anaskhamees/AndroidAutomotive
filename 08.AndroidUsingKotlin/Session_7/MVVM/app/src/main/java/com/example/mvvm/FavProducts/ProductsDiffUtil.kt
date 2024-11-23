package com.example.mvvm.FavProducts

import androidx.recyclerview.widget.DiffUtil
import com.example.mvvm.Product

class ProductsDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
    return oldItem == newItem
    }

}