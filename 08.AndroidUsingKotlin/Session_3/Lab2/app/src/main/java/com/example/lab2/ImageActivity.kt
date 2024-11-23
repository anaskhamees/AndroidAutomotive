package com.example.lab2

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class ImageActivity : AppCompatActivity() {

    private lateinit var imageView:ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.display_image)

        // Get the image path from the intent
        val imagePath = intent.getStringExtra("image_path") ?: return

        // Load the image from the file
        val imageFile = File(imagePath)
        if (imageFile.exists()) {
            val bitmap = BitmapFactory.decodeFile(imageFile.absolutePath)
             imageView= findViewById(R.id.imageViewID)
            imageView.setImageBitmap(bitmap)
        }

    }
}