package com.example.nsnik.app

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toast = Toast.makeText(
            applicationContext,
            "15 минут уже прошло",
            Toast.LENGTH_LONG
        )
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()

        Log.d("L1", "onCreate")

        val batteryButton = findViewById<Button>(R.id.batteryBtn)
        batteryButton.setOnClickListener {
            startActivity(Intent(this, BatteryActivity::class.java))
        }

        val locationButton = findViewById<Button>(R.id.locationBtn)
        locationButton.setOnClickListener {
            startActivity(Intent(this, LocationActivity::class.java))
        }

        val apiButton = findViewById<Button>(R.id.apiBtn)
        apiButton.setOnClickListener {
            startActivity(Intent(this, DomainSearchActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()

        Log.d("L1", "onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d("L1", "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d("L1", "onDestroy")
    }

    override fun onStop() {
        super.onStop()

        Log.d("L1", "onStop")
    }

    override fun onPause() {
        super.onPause()

        Log.d("L1", "onPause")
    }
}
