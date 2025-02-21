package com.example.kalkulacka

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Načtení UI prvků
        val num1 = findViewById<EditText>(R.id.num1)
        val num2 = findViewById<EditText>(R.id.num2)
        val spinnerOperations = findViewById<Spinner>(R.id.spinner_operations)
        val btnCalculate = findViewById<Button>(R.id.btn_calculate)
        val txtResult = findViewById<TextView>(R.id.txt_result)

        btnCalculate.setOnClickListener {
            // Získání hodnot z inputů
            val input1 = num1.text.toString()
            val input2 = num2.text.toString()
            val selectedOperation = spinnerOperations.selectedItem.toString()

            try {
                // Převod vstupů na čísla, pokud jsou zadané
                val number1 = input1.toDoubleOrNull()
                val number2 = input2.toDoubleOrNull()

                if (number1 == null) {
                    txtResult.text = "Chyba: Zadejte první číslo!"
                    return@setOnClickListener
                }

                var result: Double? = null

                when (selectedOperation) {
                    "Sčítání (+)" -> result = number1 + (number2 ?: 0.0)
                    "Odčítání (-)" -> result = number1 - (number2 ?: 0.0)
                    "Násobení (*)" -> result = number1 * (number2 ?: 0.0)
                    "Dělení (/)" -> {
                        if (number2 == null || number2 == 0.0) {
                            txtResult.text = "Chyba: Nelze dělit nulou!"
                            return@setOnClickListener
                        }
                        result = number1 / number2
                    }
                    "Modulo (%)" -> {
                        if (number2 == null || number2 == 0.0) {
                            txtResult.text = "Chyba: Nelze dělit nulou!"
                            return@setOnClickListener
                        }
                        result = number1 % number2
                    }
                    "Ntá mocnina (xⁿ)" -> {
                        if (number2 == null) {
                            txtResult.text = "Chyba: Zadejte exponent!"
                            return@setOnClickListener
                        }
                        result = number1.pow(number2)
                    }
                    "Ntá odmocnina (ⁿ√x)" -> {
                        if (number2 == null || number2 <= 0) {
                            txtResult.text = "Chyba: Exponent musí být kladný!"
                            return@setOnClickListener
                        }
                        result = number1.pow(1.0 / number2)
                    }
                    "Faktoriál (n!)" -> {
                        if (number1 < 0 || number1 % 1 != 0.0) {
                            txtResult.text = "Chyba: Faktoriál lze spočítat jen pro nezáporná celá čísla!"
                            return@setOnClickListener
                        }
                        result = factorial(number1.toInt()).toDouble()
                    }
                }

                // Zobrazení výsledku
                txtResult.text = "Výsledek: ${result?.toString() ?: "Neplatná operace"}"

            } catch (e: Exception) {
                txtResult.text = "Chyba: Neplatný vstup!"
            }
        }
    }

    // Funkce pro výpočet faktoriálu
    private fun factorial(n: Int): Long {
        return if (n <= 1) 1 else n * factorial(n - 1)
    }
}>