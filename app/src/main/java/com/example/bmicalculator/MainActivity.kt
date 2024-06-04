package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var welcomeTextView: TextView
    lateinit var clickButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        welcomeTextView = findViewById(R.id.welcomeTextView)
        clickButton = findViewById(R.id.clickButton)

        welcomeTextView.text = getString(R.string.main_activity_welcome_text)

        clickButton.setOnClickListener {
            welcomeTextView.text = getString(R.string.main_activity_click_text)
        }
    }
}