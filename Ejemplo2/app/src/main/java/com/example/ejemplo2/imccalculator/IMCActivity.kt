package com.example.ejemplo2.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.ejemplo2.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.internal.ViewUtils.getBackgroundColor
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class IMCActivity : AppCompatActivity() {

    private var isMaleSelected:Boolean = true
    private var isFemaleSelected:Boolean = false
    private var currentWeight:Int = 60
    private var currentAge:Int = 20
    private var currentHeight:Int = 120

    private lateinit var viewMale:CardView
    private lateinit var viewFemale:CardView

    private lateinit var tvHeight:TextView
    private lateinit var rsHeight: RangeSlider

    private lateinit var fabMinusWeight: FloatingActionButton
    private lateinit var fabPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView

    private lateinit var tvAge: TextView
    private lateinit var fabMinusAge: FloatingActionButton
    private lateinit var fabPlusAge: FloatingActionButton

    private lateinit var btncalculate:Button

    companion object{
        const val IMC_KEY = "IMC_Result"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imcactivity)
        initComponents ()
        initListeners ()
        initUi ()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.height)
        rsHeight = findViewById(R.id.rsHeight)
        fabMinusWeight = findViewById(R.id.fabMinusWeight)
        fabPlusWeight = findViewById(R.id.fabPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        tvAge = findViewById(R.id.tvAge)
        fabMinusAge = findViewById(R.id.fabMinusAge)
        fabPlusAge = findViewById(R.id.fabPlusAge)
        btncalculate = findViewById(R.id.btncalculate)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender ()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm"
        }

        fabMinusWeight.setOnClickListener{
            currentWeight -= 1
            setWeight()
        }

        fabPlusWeight.setOnClickListener{
            currentWeight += 1
            setWeight()
        }

        fabMinusAge.setOnClickListener{
            currentAge -= 1
            setAge()
        }

        fabPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }

        btncalculate.setOnClickListener {
            val result = calculateIMS()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result:Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMS(): Double {
        val df = DecimalFormat("#.##")
        val imc = currentWeight / (currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()

    }

    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }

    private fun changeGender (){
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected

    }

    private fun setGenderColor(){
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent:Boolean) : Int{
        val colorReference = if(isSelectedComponent){
            R.color.bacground_component_selected
        }else{
            R.color.bacground_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUi() {
        setGenderColor()
        setWeight()
        setAge()
    }

}