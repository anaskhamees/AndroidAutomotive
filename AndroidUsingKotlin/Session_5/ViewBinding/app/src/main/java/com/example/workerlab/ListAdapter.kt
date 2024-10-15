package com.example.workerlab

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.workerlab.databinding.ItemProductBinding // View Binding import

// DiffUtil to efficiently manage list changes
class MyDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }
}

class ListAdapterDemo(private val myListener: (Product) -> Unit) :
    ListAdapter<Product, ListAdapterDemo.ViewHolder>(MyDiffUtil()) {

    // ViewHolder with View Binding
    class ViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product, myListener: (Product) -> Unit) {
            binding.tvTitle.text = product.title
            binding.tvPrice.text = product.price.toString()

            // Set click listener for the item
            binding.clConstraint.setOnClickListener {
                myListener(product)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Inflate the layout using View Binding
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.bind(currentProduct, myListener)
    }
}
