package com.example.workerlab

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager

internal interface Communicator {
    fun setText(str: String)
}

class MainActivity : AppCompatActivity() ,Communicator{

    var fgr: FragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

    }

    override fun setText(str: String) {
        Log.i("TAG", "setText: $str")
        val frag:Fragment2 = fgr.findFragmentById(R.id.fragmentContainerView5) as Fragment2
    }

}