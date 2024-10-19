package com.example.mvvm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mvvm.AllProducts.AllProducts
import com.example.mvvm.FavProducts.AllFavProducts

class MainActivity : AppCompatActivity() {

    private lateinit var  btnAllProducts: Button
    private lateinit var  btnFavProducts: Button
    private lateinit var  btnExit   : Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnAllProducts = findViewById(R.id.btnAllProductsID)
        btnFavProducts = findViewById(R.id.btnFavProductsID)
        btnExit = findViewById(R.id.btnExitID)

        btnAllProducts.setOnClickListener {
            val outIntent = Intent(this@MainActivity, AllProducts::class.java)
            startActivity(outIntent)
        }

        btnFavProducts.setOnClickListener {
            val outIntent = Intent(this@MainActivity, AllFavProducts::class.java)
            startActivity(outIntent)
        }

        btnExit.setOnClickListener {
               finish()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}