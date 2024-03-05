package com.example.prac_3_auten_biometrica.Autenticado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.example.prac_3_auten_biometrica.Autenticado.Navigate.AceleracionLuzActivity
import com.example.prac_3_auten_biometrica.Autenticado.Navigate.AcelerometroActivity
import com.example.prac_3_auten_biometrica.Autenticado.Navigate.GiroscopioActivity
import com.example.prac_3_auten_biometrica.R

class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnAcelerometro = findViewById<AppCompatButton>(R.id.btnAcelerometro)
        val btnAceleracion_luz = findViewById<AppCompatButton>(R.id.btnAceleracion_luz)
        val btnGiroscopio = findViewById<AppCompatButton>(R.id.btnGiroscopio)

        btnAcelerometro.setOnClickListener { navigateToAcelerometro() }
        btnAceleracion_luz.setOnClickListener { navigateToAceleracion_luz() }
        btnGiroscopio.setOnClickListener { navigateToGiroscopio() }
    }

    private fun navigateToGiroscopio() {
        startActivity(Intent(this, GiroscopioActivity::class.java))
    }

    private fun navigateToAceleracion_luz() {
        startActivity(Intent(this, AceleracionLuzActivity::class.java))

    }

    private fun navigateToAcelerometro() {
        startActivity(Intent(this, AcelerometroActivity::class.java))
    }

}