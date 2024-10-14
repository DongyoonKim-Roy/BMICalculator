package com.example.bmicalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.bmicalculator.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(viewBinding.root)
        viewBinding.calculate.setOnClickListener(){
            calculateBMI()
        }

    }

    private fun calculateBMI() {
        val weight = viewBinding.weightEdit.text.toString().toFloatOrNull()
        val height = viewBinding.heightEdit.text.toString().toFloatOrNull()

        if ((weight != null && height != null) && (weight > 0 && height > 0)) {
            val bmi = weight / (height / 100).pow(2)
            val bmiResult = String.format("%.2f", bmi)

            val bmiCategory = when {
                bmi < 18.5 -> "Under Weight"
                bmi < 25 -> "Normal Weight"
                bmi < 30 -> "Over Weight"
                else -> "Obese"
            }
            viewBinding.result.text = "BMI : $bmiResult\nCategory : $bmiCategory"
        } else {
            viewBinding.result.text = "Invalid Input"
        }
    }
}