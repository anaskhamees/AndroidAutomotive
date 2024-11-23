package com.example.lab1

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentA : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var recyclerAdapter: ProductListAdapter
    private var listener: OnProductClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnProductClickListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnProductClickListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_a, container, false)

        recyclerView = view.findViewById(R.id.recycleViewID)
        recyclerAdapter = ProductListAdapter { product ->if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Launch ActivityB in portrait mode
            val intent = Intent(getActivity(), ActivityB::class.java)
            intent.putExtra("product_key", product)
            startActivity(intent)
        } else {
            // Update FragmentB with product details in landscape mode
            val fragmentB = FragmentB().apply {
                arguments = Bundle().apply {
                    putSerializable("product_key", product)
                }
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, fragmentB)
                .commit()
        }
        }

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)

        // Submit the product list to the adapter
        recyclerAdapter.submitList(RecyclerRepo.getProducts())

        return view
    }

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }


    // Handle item click from the adapter (FragmentA's RecyclerView)
    fun onProductClick(product: Product) {

    }
}
