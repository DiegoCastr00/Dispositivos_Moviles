package com.example.ejemplo2.Carpeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.ejemplo2.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val txtResult = findViewById<TextView>(R.id.txtResult)

        val name = intent.extras?.getString("EXTRA_NAME".orEmpty())

        txtResult.text = "Hola, ${name}"
    }
}