package com.example.calculatorapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import java.lang.ArithmeticException
import kotlin.properties.Delegates
import android.widget.Toast

class BasicCalcActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var firstNumber by Delegates.notNull<Double>()
    private var secondNumber by Delegates.notNull<Double>()
    private var actualOperation : Int = 0
    private var actualSign : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.basic)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)
        val button0 = findViewById<Button>(R.id.button0)
        val buttonClear = findViewById<Button>(R.id.clearButton)
        val buttonAllClear = findViewById<Button>(R.id.allClearButton)
        val buttonSign = findViewById<Button>(R.id.changeSignButton)
        val buttonResult = findViewById<Button>(R.id.buttonEqual)
        val buttonComa = findViewById<Button>(R.id.buttonComa)
        val buttonPlus = findViewById<Button>(R.id.buttonPlus)
        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        val buttonDivide = findViewById<Button>(R.id.buttonDivide)

        this.display = findViewById(R.id.resultOnScreen)
        button1.setOnClickListener { appendToDisplay("1")}
        button2.setOnClickListener { appendToDisplay("2")}
        button3.setOnClickListener { appendToDisplay("3")}
        button4.setOnClickListener { appendToDisplay("4")}
        button5.setOnClickListener { appendToDisplay("5")}
        button6.setOnClickListener { appendToDisplay("6")}
        button7.setOnClickListener { appendToDisplay("7")}
        button8.setOnClickListener { appendToDisplay("8")}
        button9.setOnClickListener { appendToDisplay("9")}
        button0.setOnClickListener { appendToDisplay("0")}
        buttonComa.setOnClickListener { appendToDisplay(".")}
        buttonPlus.setOnClickListener { setOperation(1) }
        buttonMinus.setOnClickListener { setOperation(2) }
        buttonMultiply.setOnClickListener { setOperation(3) }
        buttonDivide.setOnClickListener { setOperation(4) }
        buttonResult.setOnClickListener { countResult() }
        buttonSign.setOnClickListener { changeSign() }
        buttonClear.setOnClickListener { display.text = "" }
        buttonAllClear.setOnClickListener { clearAll() }
    }

    private fun appendToDisplay(value: String) {
        val currentDisplayText = display.text.toString()
        if(value == ".") {
            if(currentDisplayText.contains(".")) return
        }
        val newDisplayText = currentDisplayText + value
        display.text = newDisplayText
    }


    private fun setOperation(operation: Int) {
        val currentDisplayText = display.text.toString()
        firstNumber = currentDisplayText.toDouble()
        actualOperation = operation
        display.text = ""
    }

    private fun countResult() {
        actualSign = 1
        var result: Double = 0.0
        val currentDisplayText = display.text.toString()
        secondNumber = currentDisplayText.toDouble()
        when(actualOperation){
            1 -> result = firstNumber + secondNumber
            2 -> result = firstNumber - secondNumber
            3 -> result = firstNumber * secondNumber
            4 -> {
                try {
                    result = firstNumber / secondNumber
                }
                catch (e: ArithmeticException){
                    Toast.makeText(this, "Nie można dzielić przez zero!",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
        if(result.rem(1.0) == 0.0){
            display.text = result.toInt().toString()
        }
        else{
            display.text = result.toString()
        }
    }

    private fun changeSign() {
        val currentDisplayText = display.text.toString()
        var newDisplayText = ""
        if(actualSign == 1) {
            newDisplayText = "-$currentDisplayText"
            actualSign = -1
        }
        else {
            newDisplayText = currentDisplayText.drop(1)
            actualSign = 1
        }
        display.text = newDisplayText
    }

    private fun clearAll() {
        firstNumber = 0.0
        secondNumber = 0.0
        actualSign = 1
        actualOperation = 0
        display.text = ""
    }
}