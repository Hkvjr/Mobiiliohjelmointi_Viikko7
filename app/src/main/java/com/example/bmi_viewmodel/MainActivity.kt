package com.example.bmi_viewmodel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi_viewmodel.ui.theme.BMI_ViewModelTheme

class MainActivity : ComponentActivity() {
    private val bmiViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMI_ViewModelTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BmiScreen(viewModel = bmiViewModel)
                }
            }
        }
    }

    @Composable
    fun BmiScreen(viewModel: MyViewModel) {
        // Haetaan näkyville pituus-, paino- ja BMI-tulokset ViewModelista
        val heightInput = viewModel.heightInput
        val weightInput = viewModel.weightInput
        val bmiResult = viewModel.bmiResult
        val errorMessage = viewModel.errorMessage

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Body Mass Index",
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp)
            )

            OutlinedTextField(
                value = heightInput,  // Käyttäjän syöttämä pituus
                onValueChange = { viewModel.updateHeightInput(it.replace(',', '.')) },
                label = { Text(text = "Height (m)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = weightInput, // Käyttäjän syöttämä paino
                onValueChange = { viewModel.updateWeightInput(it.replace(',', '.')) },
                label = { Text(text = "Weight (kg)") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            val bmiDisplayText = if (bmiResult > 0) {
                "BMI: ${String.format("%.2f", bmiResult)}"
            } else {
                // Näytetään virheviesti, jos syötteet eivät ole kelvollisia
                errorMessage ?: "Please enter valid height and weight."
            }

            Text(text = bmiDisplayText)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        BMI_ViewModelTheme {
            BmiScreen(viewModel = MyViewModel())
        }
    }
}