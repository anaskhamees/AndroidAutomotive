package com.example.workmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android_kotlin.OnProductFavClickListener
import com.example.android_kotlin.ProductsDiffUtil
import com.example.mvvm.Product
import com.example.mvvm.R


class ProductAdapter(private val listener: OnProductFavClickListener) : ListAdapter<Product, ProductAdapter.ViewHolder>(
    ProductsDiffUtil()
) {


    class ViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        val productName: TextView = item.findViewById(R.id.editTitle)
        val imageView: ImageView = item.findViewById(R.id.imageView2)
        val price: TextView = item.findViewById(R.id.editPrice)
        val brand : TextView = item.findViewById(R.id.editBrand)
        var btnAdd : Button  = item.findViewById(R.id.btnAdd)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentProduct = getItem(position)
        holder.productName.text = currentProduct.title
        holder.price.text = currentProduct.price
        holder.brand.text = currentProduct.brand

        Glide.with(holder.imageView.context)
            .load(currentProduct.thumbnail)
            .into(holder.imageView)
        holder.btnAdd.setOnClickListener {
            listener.onProductClick(currentProduct)
        }


    }
}
