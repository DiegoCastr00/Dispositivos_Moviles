package com.dante.conversor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private lateinit var btnDistancia : ImageView
    private lateinit var btnTemperatura: ImageView
    private lateinit var btnMasa: ImageView
    private lateinit var btnTiempo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnDistancia = findViewById(R.id.btnDistancia)
        btnTemperatura = findViewById(R.id.btnTemperatura)
        btnMasa = findViewById(R.id.btnMasa)
        btnTiempo = findViewById(R.id.btnTiempo)

        btnDistancia.setOnClickListener {
            val intent = Intent(this, DistanciaActivity::class.java)
            startActivity(intent)
        }

        btnTemperatura.setOnClickListener {
            val intent = Intent(this, TemperaturaActivity::class.java)
            startActivity(intent)
        }


        btnMasa.setOnClickListener {
            val intent = Intent(this, MasaActivity::class.java)
            startActivity(intent)
        }

        btnTiempo.setOnClickListener {
            val intent = Intent(this, TiempoActivity::class.java)
            startActivity(intent)
        }
    }
}