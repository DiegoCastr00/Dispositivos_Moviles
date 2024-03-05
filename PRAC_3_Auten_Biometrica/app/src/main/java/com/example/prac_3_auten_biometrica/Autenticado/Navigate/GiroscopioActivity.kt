package com.example.prac_3_auten_biometrica.Autenticado.Navigate

import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.prac_3_auten_biometrica.R
class GiroscopioActivity : AppCompatActivity() {
    var textX: TextView? = null
    var textY: TextView? = null
    var textZ: TextView? = null
    var sensorManager: SensorManager? = null
    var sensor: Sensor? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giroscopio)
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        sensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        textX = findViewById<TextView>(R.id.textX)
        textY = findViewById<TextView>(R.id.textY)
        textZ = findViewById<TextView>(R.id.textZ)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    public override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(gyroListener, sensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    public override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(gyroListener)
    }

    var gyroListener: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor, acc: Int) {}
        override fun onSensorChanged(event: SensorEvent) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            textX!!.text = "X : " + x.toInt() + " rad/s"
            textY!!.text = "Y : " + y.toInt() + " rad/s"
            textZ!!.text = "Z : " + z.toInt() + " rad/s"
        }
    }
}