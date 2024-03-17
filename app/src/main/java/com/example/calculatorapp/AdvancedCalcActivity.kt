package com.example.calculatorapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.*
import kotlin.properties.Delegates

class AdvancedCalcActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private var firstNumber by Delegates.notNull<Double>()
    private var secondNumber by Delegates.notNull<Double>()
    private var actualOperation : Int = 0
    private var actualSign : Int = 1
    private var clearFlag : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.advanced)

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
        val buttonPercent = findViewById<Button>(R.id.buttonPercent)
        val buttonSinus = findViewById<Button>(R.id.buttonSinus)
        val buttonCosinus = findViewById<Button>(R.id.buttonCosinus)
        val buttonTangens = findViewById<Button>(R.id.buttonTangens)
        val buttonPow2 = findViewById<Button>(R.id.buttonPow2)
        val buttonPowY = findViewById<Button>(R.id.buttonPowY)
        val buttonLn = findViewById<Button>(R.id.buttonLn)
        val buttonLog = findViewById<Button>(R.id.buttonLog)
        val buttonSqrt = findViewById<Button>(R.id.buttonSqrt)

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
        buttonPercent.setOnClickListener { convertTo(1) }
        buttonSinus.setOnClickListener { convertTo(2) }
        buttonCosinus.setOnClickListener { convertTo(3) }
        buttonTangens.setOnClickListener { convertTo(4) }
        buttonPow2.setOnClickListener { convertTo(5) }
        buttonPowY.setOnClickListener { convertTo(6) }
        buttonLn.setOnClickListener { convertTo(7) }
        buttonLog.setOnClickListener { convertTo(8) }
        buttonSqrt.setOnClickListener { convertTo(9) }

        buttonResult.setOnClickListener { countResult() }
        buttonSign.setOnClickListener { changeSign() }
        buttonClear.setOnClickListener { clear() }
        buttonAllClear.setOnClickListener { clearAll() }
    }

    private fun appendToDisplay(value: String) {
        if(actualOperation == 5) {
            display.text = ""
            actualOperation = 0
        }

        val currentDisplayText = display.text.toString()
        if(value == ".") {
            if(currentDisplayText.contains(".")) return
        }
        val newDisplayText = currentDisplayText + value
        display.text = newDisplayText
    }


    private fun setOperation(operation: Int) {
        checkClear()
        val currentDisplayText = display.text.toString()
        if(actualOperation != 0){
            if(currentDisplayText.isNotEmpty()){
                multiCountingResult(currentDisplayText)
            }
        }
        else {
            if(currentDisplayText.isNotEmpty()) {
                firstNumber = currentDisplayText.toDouble()
            }
        }
        actualOperation = operation
        display.text = ""
    }

    private fun convertTo(operation: Int){
        val currentDisplayText = display.text.toString()
        var result: Double = 0.0
        if(currentDisplayText.isNotEmpty()){
            var num = currentDisplayText.toDouble()

            when(operation){
                1 -> result = num * 0.01
                2 -> result = sin(num)
                3 -> result = cos(num)
                4 -> result = tan(num)
                5 -> result = num.pow(2)
                6 -> {
                    //TODO:pieriwastek
                }
                7 -> {
                    if(num < 0.0){
                        display.text = ""
                        Toast.makeText(applicationContext, "Błędne dane",
                            Toast.LENGTH_SHORT).show()
                        return
                    }
                    result = ln(num)
                }
                8 -> {
                    if(num < 0.0){
                        display.text = ""
                        Toast.makeText(applicationContext, "Błędne dane",
                            Toast.LENGTH_SHORT).show()
                        return
                    }
                    result = log(10.0, num)
                }
                9 -> {
                    if(num < 0.0){
                        display.text = ""
                        Toast.makeText(applicationContext, "Błędne dane",
                            Toast.LENGTH_SHORT).show()
                        return
                    }
                    result = sqrt(num)
                }
            }
        }
        display.text = result.toString()
    }

    private fun multiCountingResult(numb: String){
        when(actualOperation){
            1 -> firstNumber += numb.toDouble()
            2 -> firstNumber -= numb.toDouble()
            3 -> firstNumber *= numb.toDouble()
            4 -> {
                try {
                    Log.v("dzielenie","wlazlem")
                    firstNumber /= numb.toDouble()
                }
                catch (e: ArithmeticException){
                    //TODO: POPRAWIĆ BO NIE DZIAŁA
                    Log.v("dzielenie","wlazlem w e")
                    Toast.makeText(this, "Nie można dzielić przez zero!",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun countResult() {
        actualSign = 1
        var result: Double = 0.0
        val currentDisplayText = display.text.toString()
        if(currentDisplayText.isNotEmpty()){
            secondNumber = currentDisplayText.toDouble()
        }
        when(actualOperation){
            0 -> {
                result = if(currentDisplayText.isNotEmpty()){
                    currentDisplayText.toDouble()
                } else {
                    0.0
                }
            }
            1 -> result = firstNumber + secondNumber
            2 -> result = firstNumber - secondNumber
            3 -> result = firstNumber * secondNumber
            4 -> {
                if(secondNumber == 0.0){
                    display.text = ""
                    Toast.makeText(applicationContext, "Nie można dzielić przez zero!",
                        Toast.LENGTH_SHORT).show()
                    return
                }
                result = firstNumber / secondNumber
            }
        }

        actualSign = if(result < 0) -1
        else 1

        actualOperation = 0
        if(result.rem(1.0) == 0.0){
            display.text = result.toInt().toString()
        }
        else{
            display.text = result.toString()
        }
    }

    private fun changeSign() {
        checkClear()
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

    private fun clear() {
        if(clearFlag) {
            clearAll()
            clearFlag = false
            return
        }
        display.text = ""
        clearFlag = true
    }

    private fun checkClear(){
        if(clearFlag) clearFlag = false
    }
}