package com.example.workerlab

import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.workerlab.databinding.Fragment2Binding // View Binding import
import java.net.URL
import kotlin.concurrent.thread

class Fragment2 : Fragment() {

    private lateinit var binding: Fragment2Binding // View Binding reference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Initialize View Binding
        binding = Fragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Handle the intent for portrait mode
        if (resources.configuration.orientation != ORIENTATION_LANDSCAPE) {
            val intent = this.activity?.intent
            if (intent != null) {
                // Set the product name and price
                val name = intent.getStringExtra("name")
                val price = intent.getStringExtra("price")
                val imageUrl = intent.getStringExtra("image")

                binding.tvProductName.text = name
                binding.tvProductPrice.text = "$price USD"

                // Load the image from URL in a background thread
                if (imageUrl != null) {
                    loadProductImage(imageUrl)
                }
            }
        }
    }

    private fun loadProductImage(imageUrl: String) {
        // Load image in background thread to avoid blocking the UI thread
        thread {
            try {
                val url = URL(imageUrl)
                val bmp: Bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())

                // Update UI on the main thread
                activity?.runOnUiThread {
                    binding.productImage.setImageBitmap(bmp)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // This function can be called to update the TextViews from another part of the app
    fun updateProductInfo(name: String, price: String, imageUrl: String) {
        binding.tvProductName.text = name
        binding.tvProductPrice.text = "$price USD"
        loadProductImage(imageUrl)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
