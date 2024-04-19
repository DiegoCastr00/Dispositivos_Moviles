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

class MasaActivity : AppCompatActivity() {

    private lateinit var tonelada: TextView
    private lateinit var kilogramos: TextView
    private lateinit var gramos: TextView
    private lateinit var miligramos: TextView
    private lateinit var microgramos: TextView
    private lateinit var libra: TextView
    private lateinit var onza: TextView

    private lateinit var input: EditText
    private lateinit var unit: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_masa)

        tonelada = findViewById(R.id.tonelada)
        kilogramos = findViewById(R.id.kilogramos)
        gramos = findViewById(R.id.gramos)
        miligramos = findViewById(R.id.miligramos)
        microgramos = findViewById(R.id.microgramos)
        libra = findViewById(R.id.libra)
        onza = findViewById(R.id.onza)

        input = findViewById(R.id.input)
        unit = findViewById(R.id.spinner)

        val arr = arrayOf("t", "kg", "g", "mg", "µg", "lb", "oz")

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
                "t" -> setTonelada(`in`)
                "kg" -> setTonelada(`in` / 1000)
                "g" -> setTonelada(`in` / 1000000)
                "mg" -> setTonelada(`in` / 1000000000)
                "µg" -> setTonelada(`in` / 1000000000000)
                "lb" -> setTonelada(`in` / 2204.62)
                "oz" -> setTonelada(`in` / 35273.96)
            }
        }
    }

    private fun setTonelada(t_in: Double) {
        tonelada.text = t_in.toString()
        kilogramos.text = (t_in * 1000).toString()
        gramos.text = (t_in * 1000000).toString()
        miligramos.text = (t_in * 1000000000).toString()
        microgramos.text = (t_in * 1000000000000).toString()
        libra.text = (t_in * 2204.62).toString()
        onza.text = (t_in * 35273.96).toString()
    }
}