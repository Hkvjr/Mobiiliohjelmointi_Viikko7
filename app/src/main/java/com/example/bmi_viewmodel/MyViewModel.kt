package com.example.bmi_viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    var heightInput by mutableStateOf("") //käyttäjän syöttämä pituus
        private set

    var weightInput by mutableStateOf("") //Käyttäjän syöttämä paino
        private set


    var bmiResult by mutableStateOf(0.0) // Lasketun BMI:n tulos
        private set

    var errorMessage by mutableStateOf("") // Virheviesti syötteistä
        private set


    fun updateHeightInput(newHeight: String) {
        heightInput = newHeight
        calculateBMI()
    }

    // Metodi painon päivittämiseksi ja BMI:n laskemiseksi
    fun updateWeightInput(newWeight: String) {
        weightInput = newWeight
        calculateBMI()
    }

    private fun calculateBMI() {
        val height = heightInput.toFloatOrNull()
        val weight = weightInput.toFloatOrNull()

        if (height != null && weight != null && height > 0 && weight > 0) {
            bmiResult = ((weight / (height * height)).toDouble()) // BMI = weight / (height^2)
        } else {
            bmiResult = 0.0
            errorMessage = "Virhe: Syötä kelvolliset pituus ja paino."

        }
    }
}