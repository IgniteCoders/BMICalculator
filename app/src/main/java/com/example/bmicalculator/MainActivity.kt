package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    lateinit var heightEditText: EditText
    lateinit var weightTextView: TextView
    lateinit var minusButton: Button
    lateinit var addButton: Button
    lateinit var descriptionTextView: TextView
    lateinit var resultTextView: TextView
    lateinit var calculateButton: Button

    var height: Int = 150
    var weight: Int = 70

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heightEditText = findViewById(R.id.heightEditText)
        weightTextView = findViewById(R.id.weightTextView)
        minusButton = findViewById(R.id.minusButton)
        addButton = findViewById(R.id.addButton)
        descriptionTextView = findViewById(R.id.descriptionTextView)
        resultTextView = findViewById(R.id.resultTextView)
        calculateButton = findViewById(R.id.calculateButton)

        setHeight()
        setWeight()

        minusButton.setOnClickListener {
            weight --
            setWeight()
        }

        addButton.setOnClickListener {
            weight ++
            setWeight()
        }

        calculateButton.setOnClickListener {
            height = heightEditText.text.toString().toInt()

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
        heightEditText.setText(height.toString())
    }

    fun setWeight() {
        weightTextView.text = "$weight Kg"
    }
}