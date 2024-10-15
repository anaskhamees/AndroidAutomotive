package com.example.workerlab
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class MyDiffUtil : DiffUtil.ItemCallback<Product>(){
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem == newItem
    }

}

class ListAdapterDemo(private val myListener: (Product) -> Unit) : ListAdapter<Product ,ListAdapterDemo.ViewHolder>(MyDiffUtil()){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val priceeName: TextView = itemView.findViewById(R.id.tv_price)
        val titleName: TextView = itemView.findViewById(R.id.tv_title)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.cl_constraint)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater : LayoutInflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_language,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentlanguage = getItem(position)
        holder.titleName.text = currentlanguage.title
        holder.priceeName.text = currentlanguage.price.toString()
        holder.constraintLayout.setOnClickListener {
            myListener(currentlanguage)
        }

    }
}