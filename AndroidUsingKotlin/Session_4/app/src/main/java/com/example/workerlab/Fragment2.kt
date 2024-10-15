package com.example.workerlab

import android.content.Intent
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.net.URL
import kotlin.concurrent.thread

class Fragment2 : Fragment() {

    lateinit var productNameTextView: TextView
    lateinit var productPriceTextView: TextView
    lateinit var productImageView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_2, container, false)

        // Initialize views
        productNameTextView = view.findViewById(R.id.tv_product_name)
        productPriceTextView = view.findViewById(R.id.tv_product_price)
        productImageView = view.findViewById(R.id.product_image)

        return view
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

                productNameTextView.text = name
                productPriceTextView.text = "$price USD"

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
                    productImageView.setImageBitmap(bmp)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // This function can be called to update the TextViews from another part of the app
    fun updateProductInfo(name: String, price: String, imageUrl: String) {
        productNameTextView.text = name
        productPriceTextView.text = "$price USD"
        loadProductImage(imageUrl)
    }
}