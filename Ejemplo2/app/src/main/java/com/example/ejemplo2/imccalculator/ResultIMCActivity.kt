package com.example.ejemplo2.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.ejemplo2.R
import com.example.ejemplo2.imccalculator.IMCActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {
    private lateinit var tvResult:TextView
    private lateinit var tvIMC:TextView
    private lateinit var tvDescription:TextView
    private lateinit var btnReCalculate:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)

        val result:Double = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponent()
        initUI(result)
        initListener()
    }

    private fun initListener() {
        btnReCalculate.setOnClickListener { onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when(result){
            in 0.00..18.50 ->{
                tvResult.text = getString(R.string.title_bajo_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.title_bajo_peso))
                tvDescription.text = getString(R.string.description_bajo_peso)
            }
            in 18.50..24.99 ->{
                tvResult.text = getString(R.string.title_normal_peso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.title_normal_peso))
                tvDescription.text = getString(R.string.description_normal_peso)
            }
            in 24.99..29.99 ->{
                tvResult.text = getString(R.string.title_sobrepeso)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.title_sobrepeso))
                tvDescription.text = getString(R.string.description_Sobrepeso)
            }
            in 30.00..99.00 ->{
                tvResult.text = getString(R.string.title_obesidad)
                tvResult.setTextColor(ContextCompat.getColor(this,R.color.title_obesidad))
                tvDescription.text = getString(R.string.description_Obesidad)
            }
            else -> {
                tvResult.text = getString(R.string.error)
                tvIMC.text= getString(R.string.error)
                tvDescription.text = getString(R.string.error)
            }

        }

    }

    private fun initComponent() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)
        btnReCalculate = findViewById(R.id.btnReCalculate)

    }
}