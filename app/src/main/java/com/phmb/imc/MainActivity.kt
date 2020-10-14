package com.phmb.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.text.DecimalFormat
import android.view.View
import android.content.Context
import kotlinx.android.synthetic.main.activity_main.*
import android.view.inputmethod.InputMethodManager
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_calculate.setOnClickListener {

            val weight = edit_weight.text.toString()
            val height = edit_height.text.toString()

            val validateInputs = validateData(weight, height)

            if (validateInputs) {
                val imc = calculateIMC(weight, height)
                val df = DecimalFormat("#.00")
                text_result.text = "IMC: " + df.format(imc) + "\n" + checkIMC(imc)
            } else {
                text_result.text = "Preencha os dados!"
            }

            it.hideKeyboard()
        }
    }

    private fun validateData(weightValue: String, heightValue: String) : Boolean {
        var validateFields = true

        if(weightValue.isEmpty()) {
            validateFields = false
        } else if(heightValue.isEmpty()) {
            validateFields = false
        }

        return validateFields
    }

    private fun calculateIMC(weight: String, height: String): Double{
        val weightValue = weight.toDouble()
        val heightValue = height.toDouble()
        return (weightValue/ heightValue.pow(2.0))
    }

    private fun checkIMC(imc: Double): String =
        when (imc) {
            in 0.0..18.49 -> "Magreza"
            in 18.5..24.99 -> "Normal"
            in 25.0..29.99 -> "Sobrepeso"
            in 30.0..39.99 -> "Obesidade"
            else -> "Obesidade mórbida"
        }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}