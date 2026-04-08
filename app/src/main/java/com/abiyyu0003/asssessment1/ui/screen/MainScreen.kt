package com.abiyyu0003.asssessment1.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
            .padding(16.dp)
    ) {

        Text("Masukkan data diri kamu untuk mengetahui berat badan ideal kamu")

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                TextField(
                    value = tinggi,
                    onValueChange = { tinggi = it },
                    label = { Text("Tinggi Badan (cm)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = umur,
                    onValueChange = { umur = it },
                    label = { Text("Umur (tahun)") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text("Jenis Kelamin")

                Row(verticalAlignment = Alignment.CenterVertically) {

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
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (tinggi.isEmpty() || umur.isEmpty()) {
                    hasil = "Input tidak boleh kosong!"
                } else {
                    val t = tinggi.toFloat()
                    val u = umur.toInt()
                    val ideal = hitungBeratIdeal(t, gender, u)
                    hasil = "Berat ideal: %.1f kg".format(ideal)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Hitung Sekarang")
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (hasil.isNotEmpty()) {
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Hasil Perhitungan")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(hasil)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Tips Kesehatan")
                Spacer(modifier = Modifier.height(8.dp))
                Text("• Minum air cukup\n• Olahraga rutin\n• Jaga pola makan\n• Istirahat cukup")
            }
        }
    }
}

// ✅ HARUS DI LUAR COMPOSABLE
fun hitungBeratIdeal(tinggi: Float, gender: String, umur: Int): Float {
    var hasil = if (gender == "Pria") {
        (tinggi - 100) - (0.1f * (tinggi - 100))
    } else {
        (tinggi - 100) - (0.15f * (tinggi - 100))
    }

    if (umur < 18) {
        hasil -= 2f
    } else if (umur > 40) {
        hasil -= 1.5f
    }

    return hasil
}