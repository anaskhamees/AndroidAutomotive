// MainActivity.kt
package com.example.factorialapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputNumber = findViewById<EditText>(R.id.inputNumber)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        calculateButton.setOnClickListener {
            val number = inputNumber.text.toString().toIntOrNull()
            if (number != null && number >= 0) {
                // Initiate the calculation
                FactorialCalculator.startFactorialCalculation(number, this)
            } else {
                resultTextView.text = "Please enter a valid non-negative integer"
            }
        }
    }

    // Method for Java to call to display the result
    fun displayFactorialResult(result: Long) {
        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        resultTextView.text = "Factorial result is $result"
    }
}
