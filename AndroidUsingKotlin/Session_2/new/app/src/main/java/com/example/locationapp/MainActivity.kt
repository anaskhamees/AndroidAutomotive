package com.example.locationapp

import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings // Correct import
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import android.location.Geocoder
import android.widget.Button
import android.net.Uri
import org.osmdroid.config.Configuration
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

class MainActivity : AppCompatActivity() {
    private val REQUEST_LOCATION_CODE = 5005
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var txtLong: TextView
    private lateinit var txtLat: TextView
    private lateinit var txtAddress:TextView
    private lateinit var btnSMS:Button
    private lateinit var btnMaps:Button
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txtLong = findViewById(R.id.longTxtID)
        txtLat = findViewById(R.id.latTxtID)
        txtAddress=findViewById(R.id.addressID)
        btnSMS=findViewById(R.id.btnSMSID)
        btnMaps=findViewById(R.id.btnMapID)
        mapView=findViewById(R.id.mapView)

        // Initialize OSMDroid configuration
        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", Context.MODE_PRIVATE))
        mapView.setMultiTouchControls(true)

        btnSMS.setOnClickListener {
            openSMS()
        }

        btnMaps.setOnClickListener{
            openMap()
        }
    }

    override fun onStart() {
        super.onStart()
        if (checkPermission()) {
            if (isLocationEnabled()) {
                getFreshLocation()
            } else {
                enableLocationServices()
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ),REQUEST_LOCATION_CODE
            )
        }
    }

    fun checkPermission(): Boolean {
        var result = false
        if ((ContextCompat.checkSelfPermission(this, ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) ||
            (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            result = true
        }
        return result
    }

    fun enableLocationServices() {
        Toast.makeText(this, "Turn on the location", Toast.LENGTH_LONG).show()
        val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS) // Correct constant
        startActivity(intent)
    }

    private fun isLocationEnabled():Boolean{
        val locationManager:LocationManager=getSystemService(Context.LOCATION_SERVICE)as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

    fun getFreshLocation() {

        //1. Entry Point
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        //2. Get fresh Location
        // 2. Create a LocationRequest
        val locationRequest = LocationRequest.Builder(0) // Specify the priority
            .build()
        val locationCallback : LocationCallback=object :LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                super.onLocationResult(location)
                txtLat.text = location.lastLocation?.latitude.toString()
                txtLong.text = location.lastLocation?.longitude.toString()
                val latitudeVal = location.lastLocation?.latitude ?: 0.0 // Default to 0.0 if null
                val longitudeVal = location.lastLocation?.longitude ?: 0.0 // Default to 0.0 if null
                getAddressByGeocode(latitudeVal,longitudeVal)
            }
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        fusedLocationProviderClient.requestLocationUpdates(
            locationRequest,
            locationCallback
            ,Looper.myLooper()
        )
    }

    private fun getAddressByGeocode(latitude:Double,longitude:Double)
    {
        val geocoder=Geocoder(this, java.util.Locale.getDefault())
        val addressList=geocoder.getFromLocation(latitude,longitude,1)
        if(!addressList.isNullOrEmpty())
        {
            val address=addressList[0].getAddressLine(0)
            txtAddress.text=address
        }else{
            txtAddress.text=" Not Found"
        }
    }

    private fun openSMS()
    {
        val phoneNum="01125807964"
        val smsIntent=Intent(Intent.ACTION_SENDTO).apply {
            data= Uri.parse("smsto:$phoneNum")
            putExtra("sms_body",txtAddress.text.toString())
        }
        // List apps that can handle this Intent
        val apps = packageManager.queryIntentActivities(smsIntent, 0)
        if (apps.isEmpty()) {
            Toast.makeText(this, "No apps found to handle SMS", Toast.LENGTH_SHORT).show()
        } else {
            startActivity(smsIntent)
        }
    }


    private fun openMap() {
        val latitude = txtLat.text.toString().toDoubleOrNull()
        val longitude = txtLong.text.toString().toDoubleOrNull()
        if (latitude != null && longitude != null) {
            val geoPoint = GeoPoint(latitude, longitude)
            mapView.controller.setZoom(19.0)
            mapView.controller.setCenter(geoPoint)

            val marker = Marker(mapView)
            marker.position = geoPoint
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
            marker.title = "Current Location"
            mapView.overlays.add(marker)
        }
    }

}