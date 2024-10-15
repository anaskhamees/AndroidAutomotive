package com.example.lab1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class ActivityA : AppCompatActivity() {
    private lateinit var  fragmentManager: FragmentManager
    private lateinit var fragmentAobj:FragmentA
    private lateinit var fragmentTransaction: FragmentTransaction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_a)
        if(savedInstanceState==null)
        {
            fragmentAobj=FragmentA()
        }



    }
}