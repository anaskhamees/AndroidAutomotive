package com.example.lab1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class ActivityB: AppCompatActivity() {

    private lateinit var  fragmentManager: FragmentManager
    private lateinit var fragmentBobj:FragmentB
    private lateinit var fragmentTransaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activityb)

        if(savedInstanceState==null) {
            // Get the product passed via Intent
            val product = intent.getSerializableExtra("product_key") as? Product

            // Create a new instance of FragmentB and pass the product as an argument
            fragmentBobj = FragmentB().apply {
                arguments = Bundle().apply {
                    putSerializable("product_key", product)
                }
            }

            fragmentManager = supportFragmentManager
            fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragmentContainerID, fragmentBobj)
            fragmentTransaction.commit()
        }
    }
}
