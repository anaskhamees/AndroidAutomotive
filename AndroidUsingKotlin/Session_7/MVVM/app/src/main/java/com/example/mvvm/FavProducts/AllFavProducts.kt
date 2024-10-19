package com.example.mvvm.FavProducts

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_kotlin.OnProductFavClickListener
import com.example.mvvm.DB.ProductDao
import com.example.mvvm.DB.ProductDatabase
import com.example.mvvm.Product
import com.example.mvvm.DB.ProductLocalDataSource
import com.example.mvvm.Model.Repo
import com.example.mvvm.R
import com.example.workmanager.ProductAdapter

class AllFavProducts : AppCompatActivity() , OnProductFavClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: FavProductAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var viewModel: AllFavProductsViewModel
    lateinit var products: List<Product>
    lateinit var allFavProductsViewModelFactory: AllFavProductsViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_fav_products)



        recyclerView = findViewById(R.id.rv_fav)

        val myProductDao : ProductDao = ProductDatabase.getInstance(this).getproductDao()
        val localDataSource = ProductLocalDataSource(myProductDao)


        allFavProductsViewModelFactory = AllFavProductsViewModelFactory(Repo.getInstance(localDataSource))
        viewModel = ViewModelProvider(this ,allFavProductsViewModelFactory ).get(AllFavProductsViewModel::class.java)
        viewModel.getAllFav()

        mAdapter = FavProductAdapter(this)
        mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }

        viewModel.productList.observe(this, Observer { products ->
            mAdapter.submitList(products)
        })


    }

    override fun onProductClick(product: Product) {
        viewModel.delete(product)
        Toast.makeText(this ,"Product Deleted ", Toast.LENGTH_LONG).show()
    }
}