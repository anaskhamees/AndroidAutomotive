package com.example.lab2

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var imageReciever: ImageReciever
    private lateinit var txtURL:EditText
    private lateinit var btnDownload:Button
    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txtURL=findViewById(R.id.editTextID)
        btnDownload=findViewById(R.id.btnDownloadID)
        imageReciever=ImageReciever()
        val intentFilter=IntentFilter("DownloadedImg")
        registerReceiver(imageReciever,intentFilter, RECEIVER_EXPORTED)

        btnDownload.setOnClickListener {
                val imageUrl = txtURL.text.toString()
                if (imageUrl.isNotEmpty()) {
                    val intent = Intent(this, DownloadService::class.java)
                    intent.putExtra("image_url", imageUrl)
                    startService(intent)
                }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(imageReciever)
    }
}