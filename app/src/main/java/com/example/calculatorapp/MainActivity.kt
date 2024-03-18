package com.example.calculatorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        val basicCalcButton = findViewById<Button>(R.id.buttonBasicCalc)
        basicCalcButton.setOnClickListener {
            val intent = Intent(this, BasicCalcActivity::class.java)
            startActivity(intent)
        }
        val advancedCalcButton = findViewById<Button>(R.id.buttonAdvancedCalc)
        advancedCalcButton.setOnClickListener {
            val intent = Intent(this, AdvancedCalcActivity::class.java)
            startActivity(intent)
        }
        val aboutButton = findViewById<Button>(R.id.buttonAbout)
        aboutButton.setOnClickListener {
            val intent = Intent(this, AboutMe::class.java)
            startActivity(intent)
        }
        val exitButton = findViewById<Button>(R.id.buttonExit)
        exitButton.setOnClickListener {
            finishAffinity()
        }
    }
}