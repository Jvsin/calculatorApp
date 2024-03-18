package com.example.calculatorapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*
import kotlin.properties.Delegates

class AboutMe : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.about)

        val button1 = findViewById<Button>(R.id.close_button)
        button1.setOnClickListener { finish() }
    }
}