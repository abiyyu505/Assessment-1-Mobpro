package com.abiyyu0003.asssessment1.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
    ScreenContent( innerPadding )
}

@Composable
fun ScreenContent( innerPadding: PaddingValues ) {
    var tinggi by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("Pria") }

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

        Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
            Text("Hitung")
        }
    }
}