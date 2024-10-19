package com.example.workerlab

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

internal interface Communicator {
    fun setText(name: String, price: String, imageUrl: String)
}

class MainActivity : AppCompatActivity() ,Communicator{

    var fragmentManager: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }

    override fun setText(name: String, price: String, imageUrl: String) {
        // Find Fragment2 and update its content
        val fragment2 = fragmentManager.findFragmentById(R.id.fragment2Container) as? Fragment2
        fragment2?.updateProductInfo(name, price, imageUrl)
    }

}