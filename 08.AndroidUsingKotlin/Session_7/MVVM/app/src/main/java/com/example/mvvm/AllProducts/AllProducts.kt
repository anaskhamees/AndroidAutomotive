package com.example.mvvm.AllProducts

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

class AllProducts : AppCompatActivity() , OnProductFavClickListener {

    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: ProductAdapter
    lateinit var mLayoutManager: LinearLayoutManager
    lateinit var viewModel: AllProductsViewModel
    lateinit var products: List<Product>
    lateinit var allProductsViewModelFactory: AllProductsViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_all_products)

        recyclerView = findViewById(R.id.rv_prod)

        val myProductDao : ProductDao = ProductDatabase.getInstance(this).getproductDao()
        val localDataSource = ProductLocalDataSource(myProductDao)


        allProductsViewModelFactory = AllProductsViewModelFactory(Repo.getInstance(localDataSource))
        viewModel = ViewModelProvider(this ,allProductsViewModelFactory ).get(AllProductsViewModel::class.java)
        viewModel.getProducts()

        mAdapter = ProductAdapter(this)
        mLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recyclerView.apply {
            adapter = mAdapter
            layoutManager = mLayoutManager
        }

            viewModel.productList.observe(this, Observer { products ->
                mAdapter.submitList(products)
            })
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }

    override fun onProductClick(product: Product) {
       viewModel.insertToFav(product)
        Toast.makeText(this ,"Product added to favirotes ",Toast.LENGTH_LONG).show()
    }

}