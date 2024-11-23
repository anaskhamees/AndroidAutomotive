package com.example.lab2

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class DownloadService : IntentService("downloadService") {

    override fun onHandleIntent(intent: Intent?) {
        val imageUrl = intent?.getStringExtra("image_url")
        val bitmap = downloadImage(imageUrl)
        bitmap?.let {
            val imagePath = saveImageToExternalStorage(it)
            val broadcastIntent = Intent("DownloadedImg")
            broadcastIntent.putExtra("image_path", imagePath)
            sendBroadcast(broadcastIntent)
        }
    }

    private fun downloadImage(imageUrl: String?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            bitmap = BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    private fun saveImageToExternalStorage(bitmap: Bitmap): String {
        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        val file = File(path, "downloaded_image.png")
        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return file.absolutePath
    }

  }