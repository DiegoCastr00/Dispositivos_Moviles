package com.dante.conversor

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DistanciaActivity : AppCompatActivity() {
    private lateinit var input: EditText
    private lateinit var unit: Spinner

    private lateinit var km: TextView
    private lateinit var m: TextView
    private lateinit var cm: TextView
    private lateinit var mm: TextView
    private lateinit var microm: TextView
    private lateinit var nm: TextView
    private lateinit var mile: TextView
    private lateinit var yard: TextView
    private lateinit var foot: TextView
    private lateinit var inch: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distancia)

        km = findViewById(R.id.km)
        m = findViewById(R.id.m)
        cm = findViewById(R.id.cm)
        mm = findViewById(R.id.mm)
        microm = findViewById(R.id.microm)
        nm = findViewById(R.id.nm)
        mile = findViewById(R.id.milla)
        yard = findViewById(R.id.yarda)
        foot = findViewById(R.id.pie)
        inch = findViewById(R.id.pulgada)

        input = findViewById(R.id.input)
        unit = findViewById(R.id.spinner)

        val arr = arrayOf("km", "m", "cm", "mm", "μm", "nm", "milla", "yarda", "pie", "pulgada")

        unit.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arr)
        unit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                update()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        input.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                update()
            }
        })
    }

    private fun update() {
        if (input.text.toString() != "" && unit.selectedItem.toString() != "") {
            val `in` = input.text.toString().toDouble()
            when (unit.selectedItem.toString()) {
                "km" -> setKm(`in`)
                "m" -> setKm(`in` / 1000)
                "cm" -> setKm(`in` / 100000)
                "mm" -> setKm(`in` / 1000000)
                "μm" -> setKm(`in` / 1000000000)
                "nm" -> {
                    val d = (1000000 * 1000000).toDouble()
                    setKm(`in` / d)
                }

                "milla" -> setKm(`in` * 1.609)
                "yarda" -> setKm(`in` / 1094)
                "pie" -> setKm(`in` / 3281)
                "pulgada" -> setKm(`in` / 39370)
            }
        }
    }

    private fun setKm(km_in: Double) {
        km.text = km_in.toString()
        m.text = (km_in * 1000).toString()
        cm.text = (km_in * 100000).toString()
        mm.text = (km_in * 1000000).toString()
        microm.text = (km_in * 1000000000).toString()
        nm.text = (km_in * 1000000 * 1000000).toString()
        mile.text = (km_in / 1.609).toString()
        yard.text = (km_in * 1094).toString()
        foot.text = (km_in * 3281).toString()
        inch.text = (km_in * 39370).toString()
    }
}