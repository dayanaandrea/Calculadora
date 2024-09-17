package com.example.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var display: TextView
    private val currentInput = StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        display = findViewById(R.id.editTextText) // Aquí debe estar el ID correcto

        val buttonIds = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9 , R.id.buttonSumar , R.id.buttonRestar,
            R.id.buttonPorcentaje , R.id.buttonMulti , R.id.buttonIgual,
            R.id.buttonDivision, R.id.buttonComa , R.id.buttonAC
        )

        buttonIds.forEach { id ->
            findViewById<Button>(id).setOnClickListener { button ->
            when (button.id) {
                R.id.buttonAC -> clearInput()

                else -> appendNumber((button as Button).text.toString())
            }
        }
    }
}
    private fun clearInput() {
        println("clearInput() called")
        currentInput.clear()
        display.text = ""
    }
    private fun appendNumber(number: String) {
        currentInput.append(number)
        display.text = currentInput.toString()
    }
}


