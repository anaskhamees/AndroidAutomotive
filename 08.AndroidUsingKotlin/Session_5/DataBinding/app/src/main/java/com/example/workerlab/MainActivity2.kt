package com.example.workerlab

import android.content.Intent
import android.content.res.Configuration
import android.content.res.Configuration.ORIENTATION_LANDSCAPE
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        if (resources.configuration.orientation == ORIENTATION_LANDSCAPE) {
            finish()
        }
    }
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//
//
//        // Check if the new configuration is landscape
//        if (newConfig.orientation === Configuration.ORIENTATION_LANDSCAPE) {
//            // Finish this activity
//            finish()
//
//
//            // Launch Activity 1
//            val intent: Intent = Intent(
//                this@MainActivity2,
//                MainActivity::class.java
//            )
//            // You can pass any necessary data to Activity 1 here
//            startActivity(intent)
//        }
//    }

}