package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.slider.Slider
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    // Altura
    lateinit var heightTextView: TextView
    lateinit var heightSlider: Slider
    // Peso
    lateinit var weightTextView: TextView
    lateinit var minusButton: Button
    lateinit var addButton: Button
    // Resultado
    lateinit var descriptionTextView: TextView
    lateinit var resultTextView: TextView

    lateinit var calculateButton: Button

    var height: Int = 180
    var weight: Int = 82

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heightTextView = findViewById(R.id.heightTextView)
        heightSlider = findViewById(R.id.heightSlider)
        weightTextView = findViewById(R.id.weightTextView)
        minusButton = findViewById(R.id.minusButton)
        addButton = findViewById(R.id.addButton)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        resultTextView = findViewById(R.id.resultTextView)
        calculateButton = findViewById(R.id.calculateButton)

        heightSlider.value = height.toFloat()
        setHeight()
        setWeight()

        heightSlider.addOnChangeListener { _, value, _ ->
            height = value.toInt()
            setHeight()
        }

        minusButton.setOnClickListener {
            weight --
            setWeight()
        }

        addButton.setOnClickListener {
            weight ++
            setWeight()
        }

        calculateButton.setOnClickListener {
            val result = weight / (height / 100f).pow(2)
            val decimalFormat = DecimalFormat("#.##")

            val description: String?
            val descriptionColor: Int?

            when (result) {
                in 0f..18.5f -> {
                    description = getString(R.string.under_weight)
                    descriptionColor = getColor(R.color.under_weight)
                }
                in 18.5f..25f -> {
                    description = getString(R.string.normal_weight)
                    descriptionColor = getColor(R.color.normal_weight)
                }
                in 25f..30f -> {
                    description = getString(R.string.over_weight)
                    descriptionColor = getColor(R.color.over_weight)
                }
                else -> {
                    description = getString(R.string.obesity)
                    descriptionColor = getColor(R.color.obesity)
                }
            }

            resultTextView.text = decimalFormat.format(result)
            resultTextView.setTextColor(descriptionColor)
            descriptionTextView.text = description
            descriptionTextView.setTextColor(descriptionColor)
        }
    }

    fun setHeight() {
        heightTextView.text = getString(R.string.height_text, height)
    }

    fun setWeight() {
        weightTextView.text = getString(R.string.weight_text, weight)
    }
}