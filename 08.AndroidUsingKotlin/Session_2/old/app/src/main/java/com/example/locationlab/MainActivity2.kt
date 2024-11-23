
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
import com.example.locationlab.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices

public class MainActivity2 : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var txtLong: TextView
    private lateinit var txtLat: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        txtLong = findViewById(R.id.longTxtID)
        txtLat = findViewById(R.id.latTxtID)

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
            .setMaxUpdates(1) // Optional: Limit the number of updates
            .build()
        val locationCallback : LocationCallback=object :LocationCallback() {
            override fun onLocationResult(location: LocationResult) {
                super.onLocationResult(location)
                txtLat.text = location.lastLocation?.latitude.toString()
                txtLong.text = location.lastLocation?.longitude.toString()
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
}