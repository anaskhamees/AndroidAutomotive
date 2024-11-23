package com.example.workerlab

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.example.workerlab.databinding.ActivityMainBinding // View Binding import

internal interface Communicator {
    fun setText(str: String)
}

class MainActivity : AppCompatActivity(), Communicator {

    private lateinit var binding: ActivityMainBinding // Declare View Binding
    private var fragment: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Use binding's root view to set the content

    }

    override fun setText(str: String) {
        val frag: Fragment2 = fragment.findFragmentById(R.id.fragmentContainerView5) as Fragment2
    }
}
