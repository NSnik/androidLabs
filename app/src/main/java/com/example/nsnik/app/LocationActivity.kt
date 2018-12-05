package com.example.nsnik.app

import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.os.Process
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.location.LocationManager
import android.widget.TextView


class LocationActivity : AppCompatActivity() {

    lateinit var locatinTextView: TextView

    fun getLocation() {
        if (checkPermission(
                android.Manifest.permission.ACCESS_FINE_LOCATION,
                Process.myPid(),
                Process.myUid()
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val locManager = getSystemService(LOCATION_SERVICE) as LocationManager

            val locationListener = object : LocationListener {

                override fun onLocationChanged(location: Location) {
                    // Called when a new location is found by the network location provider.
                    val latitude = location.latitude
                    val longitude = location.longitude

                    locatinTextView.text = "$latitude $longitude"
                }

                override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
                }

                override fun onProviderEnabled(provider: String) {
                }

                override fun onProviderDisabled(provider: String) {
                }
            }

            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f, locationListener)

        } else {
            locationPermission()
        }
    }

    fun locationPermission() {
        val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(this, permissions, 0)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_location)
        locatinTextView = findViewById(R.id.locatinsText)
    }

    override fun onResume() {
        super.onResume()
        getLocation()
    }
}