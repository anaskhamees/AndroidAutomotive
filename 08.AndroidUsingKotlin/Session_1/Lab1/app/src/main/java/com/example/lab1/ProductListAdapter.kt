package com.example.lab1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder


class ProductDiffUtil:DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {

        return  oldItem.name==newItem.name
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {

        return oldItem==newItem
    }

}
class ProductListAdapter(var myListener:(Product)->Unit):ListAdapter<Product,ProductListAdapter.ViewHolder>(ProductDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductListAdapter.ViewHolder {
        val inflater:LayoutInflater=LayoutInflater.from(parent.context)
        val view =inflater.inflate(R.layout.single_cell,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductListAdapter.ViewHolder, position: Int) {
        val currentProduct =getItem(position)
        holder.productName.text=currentProduct.name
        holder.productImg.setImageResource(currentProduct.img)
        holder.itemView.setOnClickListener {
            myListener.invoke(currentProduct)
        }
    }

    class ViewHolder(private val item: View):RecyclerView.ViewHolder(item){

        val productName:TextView=item.findViewById(R.id.textViewID)
        val productImg:ImageView=item.findViewById(R.id.imageViewID)
    }
}


