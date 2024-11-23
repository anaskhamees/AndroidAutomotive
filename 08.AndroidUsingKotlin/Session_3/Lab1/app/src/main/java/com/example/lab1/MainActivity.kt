package com.example.lab1

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var myService: BoundService
    var isBound:Boolean=false
    private lateinit var txtTimeView:TextView
    private lateinit var btnTime:Button

    private var myConnection:ServiceConnection=object :ServiceConnection {

        // callBack method
        override fun onServiceConnected(name: ComponentName?, service: IBinder?/*return from on bind*/) {
            val binder:BoundService.MyLocalBinder=service as BoundService.MyLocalBinder
            myService=binder.getService()  //reference to remote object of service
            isBound=true
        }
        override fun onServiceDisconnected(name: ComponentName?) {
            isBound=false
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txtTimeView=findViewById(R.id.txtTimeID)
        btnTime=findViewById(R.id.btnTimeID)
        val intent =Intent(this,BoundService::class.java)
        bindService(intent,myConnection,Context.BIND_AUTO_CREATE)

        btnTime.setOnClickListener {
            showTime()
        }
    }

    fun showTime(){
        val currentTime:String =myService.getCurrentTime()
        txtTimeView.text=currentTime
    }

}