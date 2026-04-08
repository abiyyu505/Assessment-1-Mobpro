package com.abiyyu0003.asssessment1.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    ScreenContent(innerPadding)
}

@Composable
fun ScreenContent(innerPadding: PaddingValues) {
    var tinggi by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Pria") }
    var hasil by remember { mutableStateOf("") }
    var umur by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text("Halaman Utama")

        TextField(
            value = tinggi,
            onValueChange = { tinggi = it },
            label = { Text("Tinggi Badan (cm)") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = umur,
            onValueChange = {umur = it },
            label = { Text("Umur (tahun)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row {
            RadioButton(
                selected = gender == "Pria",
                onClick = { gender = "Pria" }
            )
            Text("Pria")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = gender == "Wanita",
                onClick = { gender = "Wanita" }
            )
            Text("Wanita")
        }

        Button(
            onClick = {
                if (tinggi.isEmpty() || umur.isEmpty()) {
                    hasil = "Input tidak boleh kosong!"
                } else {
                    val t = tinggi.toFloat()
                    val u = umur.toInt()
                    val ideal = hitungBeratIdeal(t, gender, u)
                    hasil = "Berat ideal: $ideal kg"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung")
        }

        Spacer(modifier = Modifier.height(16.dp))
            Text(hasil)
        }
    }
    fun hitungBeratIdeal(tinggi: Float, gender: String, umur: Int): Float {
        var hasil = if (gender == "Pria") {
            (tinggi - 100) - (0.1f * (tinggi - 100))
        } else {
            (tinggi - 100) - (0.15f * (tinggi - 100))
        }

        if (umur < 18) {
            hasil -= 2f
        } else if (umur > 40) {
            hasil =- 1.5f
        }

        return hasil
    }
