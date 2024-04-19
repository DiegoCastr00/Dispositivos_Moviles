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

class TiempoActivity : AppCompatActivity() {

    private lateinit var input: EditText
    private lateinit var unit: Spinner

    private lateinit var milisegundo: TextView
    private lateinit var segundo: TextView
    private lateinit var minuto: TextView
    private lateinit var hora: TextView
    private lateinit var dia: TextView
    private lateinit var semana: TextView
    private lateinit var mes: TextView
    private lateinit var ano: TextView
    private lateinit var decada: TextView
    private lateinit var siglo: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tiempo)

        milisegundo = findViewById(R.id.milisegundo)
        segundo = findViewById(R.id.segundo)
        minuto = findViewById(R.id.minuto)
        hora = findViewById(R.id.hora)
        dia = findViewById(R.id.dia)
        semana = findViewById(R.id.semana)
        mes = findViewById(R.id.mes)
        ano = findViewById(R.id.ano)
        decada = findViewById(R.id.decada)
        siglo = findViewById(R.id.siglo)

        input = findViewById(R.id.input)
        unit = findViewById(R.id.spinner)

        val arr =
            arrayOf("ms", "seg", "min", "hr", "dia", "semana", "mes", "año", "decada", "siglo")

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
                "ms" -> {
                    milisegundo.text = `in`.toString()
                    segundo.text = (`in` / 1000).toString()
                    minuto.text = (`in` / 60000).toString()
                    hora.text = (`in` / 3600000).toString()
                    dia.text = (`in` / 86400000).toString()
                    semana.text = (`in` / 604800000).toString()
                    mes.text = (`in` / 2628000000).toString()
                    ano.text = (`in` / 31536000000).toString()
                    decada.text = (`in` / 315360000000).toString()
                    siglo.text = (`in` / 3153600000000).toString()
                }

                "seg" -> {
                    val s = `in`
                    milisegundo.text = (s * 1000).toString()
                    segundo.text = s.toString()
                    minuto.text = (s / 60).toString()
                    hora.text = (s / 3600).toString()
                    dia.text = (s / 86400).toString()
                    semana.text = (s / 604800).toString()
                    mes.text = (s / 2628000).toString()
                    ano.text = (s / 31536000).toString()
                    decada.text = (s / 315360000).toString()
                    siglo.text = (s / 3153600000).toString()
                }

                "min" -> {
                    val m = `in`
                    milisegundo.text = (m * 60000).toString()
                    segundo.text = (m * 60).toString()
                    minuto.text = m.toString()
                    hora.text = (m / 60).toString()
                    dia.text = (m / 1440).toString()
                    semana.text = (m / 10080).toString()
                    mes.text = (m / 43800).toString()
                    ano.text = (m / 525600).toString()
                    decada.text = (m / 5256000).toString()
                    siglo.text = (m / 52560000).toString()
                }

                "hr" -> {
                    val h = `in`
                    milisegundo.text = (h * 3600000).toString()
                    segundo.text = (h * 3600).toString()
                    minuto.text = (h * 60).toString()
                    hora.text = h.toString()
                    dia.text = (h / 24).toString()
                    semana.text = (h / 168).toString()
                    mes.text = (h / 730).toString()
                    ano.text = (h / 8760).toString()
                    decada.text = (h / 87600).toString()
                    siglo.text = (h / 876000).toString()
                }

                "dia" -> {
                    val d = `in`
                    milisegundo.text = (d * 86400000).toString()
                    segundo.text = (d * 86400).toString()
                    minuto.text = (d * 1440).toString()
                    hora.text = (d * 24).toString()
                    dia.text = d.toString()
                    semana.text = (d / 7).toString()
                    mes.text = (d / 30.417).toString()
                    ano.text = (d / 365).toString()
                    decada.text = (d / 3650).toString()
                    siglo.text = (d / 36500).toString()
                }

                "semana" -> {
                    val w = `in`
                    milisegundo.text = (w * 604800000).toString()
                    segundo.text = (w * 604800).toString()
                    minuto.text = (w * 10080).toString()
                    hora.text = (w * 168).toString()
                    dia.text = (w * 7).toString()
                    semana.text = w.toString()
                    mes.text = (w / 4.345).toString()
                    ano.text = (w / 52.143).toString()
                    decada.text = (w / 521.429).toString()
                    siglo.text = (w / 5214.286).toString()
                }

                "mes" -> {
                    val mo = `in`
                    milisegundo.text = (mo * 2628000000).toString()
                    segundo.text = (mo * 2628000).toString()
                    minuto.text = (mo * 43800).toString()
                    hora.text = (mo * 730).toString()
                    dia.text = (mo * 30.417).toString()
                    semana.text = (mo * 4.345).toString()
                    mes.text = mo.toString()
                    ano.text = (mo / 12).toString()
                    decada.text = (mo / 120).toString()
                    siglo.text = (mo / 1200).toString()
                }

                "año" -> {
                    val y = `in`
                    milisegundo.text = (y * 31536000000).toString()
                    segundo.text = (y * 31536000).toString()
                    minuto.text = (y * 525600).toString()
                    hora.text = (y * 8760).toString()
                    dia.text = (y * 365).toString()
                    semana.text = (y * 52.143).toString()
                    mes.text = (y * 12).toString()
                    ano.text = y.toString()
                    decada.text = (y / 10).toString()
                    siglo.text = (y / 100).toString()
                }

                "decada" -> {
                    val de = `in`
                    milisegundo.text = (de * 315360000000).toString()
                    segundo.text = (de * 315360000).toString()
                    minuto.text = (de * 5256000).toString()
                    hora.text = (de * 87600).toString()
                    dia.text = (de * 3650).toString()
                    semana.text = (de * 521.429).toString()
                    mes.text = (de * 120).toString()
                    ano.text = (de * 10).toString()
                    decada.text = de.toString()
                    siglo.text = (de / 10).toString()
                }

                "siglo" -> {
                    val si = `in`
                    milisegundo.text = (si * 3153600000000).toString()
                    segundo.text = (si * 3153600000).toString()
                    minuto.text = (si * 52560000).toString()
                    hora.text = (si * 876000).toString()
                    dia.text = (si * 36500).toString()
                    semana.text = (si * 5214.286).toString()
                    mes.text = (si * 1200).toString()
                    ano.text = (si * 100).toString()
                    decada.text = (si * 10).toString()
                    siglo.text = si.toString()
                }
            }
        }
    }
}
