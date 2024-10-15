package com.example.lab2

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ImageReciever : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "Image Download Completed", Toast.LENGTH_SHORT).show()

        val imagePath = intent?.getStringExtra("image_path") ?: return
        val displayIntent = Intent(context, ImageActivity::class.java).apply {
            putExtra("image_path", imagePath)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context?.startActivity(displayIntent)
    }
}