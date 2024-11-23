package com.example.workerlab

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.workerlab.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    // Declare a variable for the binding
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the binding
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
