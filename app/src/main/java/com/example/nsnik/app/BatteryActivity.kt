package com.example.nsnik.app

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import android.content.IntentFilter

class BatteryActivity : AppCompatActivity() {

    val br = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent) {
            if (intent.action == Intent.ACTION_POWER_CONNECTED) {
                Toast.makeText(context, ("Заряжается"), Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(context, "Зарядка отключена", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_battery)
        val bm = getSystemService(BATTERY_SERVICE) as BatteryManager
        val batLevel = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)

        val batteryLevel = findViewById<TextView>(R.id.batteryLevel)
        batteryLevel.text = "Остаток заряда: $batLevel%"
    }

    override fun onResume() {
        super.onResume()

        val ifilter = IntentFilter()
        ifilter.addAction(Intent.ACTION_POWER_CONNECTED)
        ifilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        registerReceiver(br, ifilter)
    }

    override fun onPause() {
        super.onPause()

        unregisterReceiver(br)
    }
}