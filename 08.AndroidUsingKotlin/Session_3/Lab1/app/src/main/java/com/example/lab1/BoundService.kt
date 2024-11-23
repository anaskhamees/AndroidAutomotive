package com.example.lab1

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BoundService : Service() {

    private val myBinder:IBinder=MyLocalBinder()

    // the return of the on bind is IBinder (Binder object) and then send Binder object to the (on service connected method)
    override fun onBind(intent: Intent): IBinder {
        return myBinder //Object type of Binder (it is implement the remote procedure call instead of me )
    }

    fun getCurrentTime():String{
        val dataFormat=SimpleDateFormat("HH:mm:ss mm/dd/yyyy",Locale.US)
        return dataFormat.format(Date())
    }

    inner class MyLocalBinder:Binder(){
        fun getService():BoundService{
            return this@BoundService
        }
    }
}