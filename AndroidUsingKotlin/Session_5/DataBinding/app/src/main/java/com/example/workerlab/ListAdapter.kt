package com.example.workerlab

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.workerlab.databinding.ItemProductBinding

// Define the DiffUtil for Product comparison
class MyDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

// The ListAdapter implementation using Data Binding
class ListAdapterDemo(private val myListener: (Product) -> Unit) :
    ListAdapter<Product, ListAdapterDemo.ViewHolder>(MyDiffUtil()) {

    // ViewHolder with Data Binding
    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {

        // Bind the product data to the layout
        fun bind(product: Product, listener: (Product) -> Unit) {
            binding.product = product // Set the product object for data binding
            binding.root.setOnClickListener { listener(product) }
            binding.executePendingBindings() // Ensure the binding is executed immediately
        }
    }

    // Inflating the layout using DataBindingUtil
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemProductBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_product, // Your XML layout file for the product item
            parent,
            false
        )
        return ViewHolder(binding)
    }

    // Bind the ViewHolder with product data
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct, myListener) // Bind the product and click listener
    }
}
