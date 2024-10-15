package com.example.workerlab

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.example.workerlab.databinding.Fragment1Binding // View Binding import
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment1 : Fragment() {

    private lateinit var recyclerAdapter: ListAdapterDemo
    private lateinit var binding: Fragment1Binding // View Binding reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize View Binding
        binding = Fragment1Binding.inflate(inflater, container, false)
        val view = binding.root

        // Set up the RecyclerView with View Binding
        recyclerAdapter = ListAdapterDemo { product ->
            val intent = Intent(requireContext(), MainActivity2::class.java).apply {
                putExtra("name", product.title)
                putExtra("price", product.price.toString())
                putExtra("image", product.thumbnail)
            }
            startActivity(intent)
        }

        binding.rvRecyclerview.apply {
            adapter = recyclerAdapter
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = RecyclerView.VERTICAL
            }
        }

        // Start the worker to fetch products
        startProductWorker()

        return view
    }

    private fun startProductWorker() {
        // Create a OneTimeWorkRequest to fetch products
        val workRequest = OneTimeWorkRequestBuilder<ProductWorker>().build()

        // Enqueue the work
        WorkManager.getInstance(requireContext()).enqueue(workRequest)

        // Observe the work status
        WorkManager.getInstance(requireContext()).getWorkInfoByIdLiveData(workRequest.id)
            .observe(viewLifecycleOwner) { workInfo ->
                if (workInfo != null && workInfo.state.isFinished) {
                    // Fetch products when work is done
                    fetchProducts()
                }
            }
    }

    private fun fetchProducts() {
        // Make a synchronous API call to get products and update RecyclerView
        RetrofitClient.api.getProducts().enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(call: Call<ProductsResponse>, response: Response<ProductsResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { productResponse ->
                        recyclerAdapter.submitList(productResponse.products)
                    }
                } else {
                    Toast.makeText(requireContext(), "Error fetching products", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Network error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
