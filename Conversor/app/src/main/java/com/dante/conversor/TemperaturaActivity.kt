package com.dante.conversor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class TemperaturaActivity : AppCompatActivity() {

    private lateinit var input: EditText
    private lateinit var unit: Spinner

    private lateinit var celsius: TextView
    private lateinit var fahrenheit: TextView
    private lateinit var kelvin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temperatura)

        input = findViewById(R.id.input)
        unit = findViewById(R.id.spinner)

        celsius = findViewById(R.id.celsius)
        fahrenheit = findViewById(R.id.fahrenheit)
        kelvin = findViewById(R.id.kelvin)

        val arr = arrayOf("°C", "°F", "°K")

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
                "°C" -> {
                    celsius.text = `in`.toString()
                    fahrenheit.text = ((`in` * 9 / 5) + 32).toString()
                    kelvin.text = (`in` + 273.15).toString()
                }
                "°F" -> {
                    val f = `in`
                    celsius.text = ((f - 32) * 5 / 9).toString()
                    fahrenheit.text = f.toString()
                    kelvin.text = ((f + 459.67) * 5 / 9).toString()
                }
                "°K" -> {
                    val k = `in`
                    celsius.text = (k - 273.15).toString()
                    fahrenheit.text = (k * 9 / 5 - 459.67).toString()
                    kelvin.text = k.toString()
                }
            }
        }
    }
}