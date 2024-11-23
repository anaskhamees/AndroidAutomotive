package com.example.mvvm.FavProducts

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


class FavProductAdapter(private val listener: OnProductFavClickListener) : ListAdapter<Product, FavProductAdapter.ViewHolder>(
    ProductsDiffUtil()
) {
    class ViewHolder(private val item: View) : RecyclerView.ViewHolder(item) {
        val productName: TextView = item.findViewById(R.id.editTitle2)
        val imageView: ImageView = item.findViewById(R.id.imageView3)
        val price: TextView = item.findViewById(R.id.editPrice2)
        val brand : TextView = item.findViewById(R.id.editBrand2)
        var btnDel : Button  = item.findViewById(R.id.btnDel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.fav_item, parent, false)
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
        holder.btnDel.text = "Delete"  // Ensure the button text is "Delete"

        holder.btnDel.setOnClickListener {
            listener.onProductClick(currentProduct)
        }


    }
}
