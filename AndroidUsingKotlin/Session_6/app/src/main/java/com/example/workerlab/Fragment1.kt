package com.example.workerlab

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Fragment1 : Fragment() {

    private lateinit var recyclerAdapter: ListAdapterDemo
    private lateinit var recyclerView: RecyclerView
    private lateinit var productDao: ProductDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        recyclerView = view.findViewById(R.id.rv_recyclerview)

        // Initialize the ProductDao
        productDao = AppDatabase.getDatabase(requireContext()).productDao()

        // Set up the RecyclerView
        recyclerAdapter = ListAdapterDemo { product ->
            if (resources.configuration.orientation != ORIENTATION_LANDSCAPE) {
                // Portrait mode: Open MainActivity2
                val intent = Intent(requireContext(), MainActivity2::class.java).apply {
                    putExtra("name", product.title)
                    putExtra("price", product.price.toString())
                    putExtra("image", product.thumbnail)
                }
                startActivity(intent)
            } else {
                // Landscape mode: Update Fragment2 in MainActivity
                (activity as? Communicator)?.let {
                    it.setText(product.title, product.price.toString(), product.thumbnail)
                }
            }
        }

        recyclerView.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.VERTICAL
            }
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fetchProducts()
    }

    private fun fetchProducts() {
        // Check network connectivity
        if (NetworkUtils.isNetworkAvailable(requireContext())) {
            // If network is available, fetch products from the API
            lifecycleScope.launch(Dispatchers.IO) {
                try {
                    val apiObj = RetrofitClient.service
                    val response = apiObj.getProducts()

                    if (response.isSuccessful) {
                        val products = response.body()?.products ?: emptyList()

                        // Save products to the database
                        productDao.deleteAll() // Clear old data
                        productDao.insertAll(products) // update all

                        // Switch to the main thread to update the UI
                        withContext(Dispatchers.Main) {
                            if (products.isNotEmpty()) {
                                recyclerAdapter.submitList(products)
                            } else {
                                Toast.makeText(requireContext(), "No products available", Toast.LENGTH_SHORT).show()
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Failed to fetch products", Toast.LENGTH_SHORT).show()
                            // If API call fails, retrieve products from the database
                            retrieveProductsFromDatabase()
                        }
                    }
                } catch (e: Exception) {
                    // Handle the exception (network errors, etc.)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "An error occurred: ${e.message}", Toast.LENGTH_LONG).show()
                        // If an error occurs, retrieve products from the database
                        retrieveProductsFromDatabase()
                    }
                }
            }
        } else {
            // If no network, retrieve products from the database
            retrieveProductsFromDatabase()
        }
    }

    private fun retrieveProductsFromDatabase() {
        lifecycleScope.launch(Dispatchers.IO) {
            val products = productDao.getAllProducts()
            // Update your UI on the main thread with the retrieved products
            withContext(Dispatchers.Main) {
                if (products.isNotEmpty()) {
                    recyclerAdapter.submitList(products)
                } else {
                    Toast.makeText(requireContext(), "No products available in the database", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun deleteAllProducts() {
        lifecycleScope.launch(Dispatchers.IO) {
            productDao.deleteAll() // Call the DAO method to delete all products
            // Clear the adapter's list after deletion
            withContext(Dispatchers.Main) {
                recyclerAdapter.submitList(emptyList()) // Update UI to reflect the deletion
                Toast.makeText(requireContext(), "All products deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
