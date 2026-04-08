package com.abiyyu0003.asssessment1.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.abiyyu0003.asssessment1.R
import com.abiyyu0003.asssessment1.navigation.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.title_main)) },
                actions = {
                    IconButton(onClick = {
                        navController.navigate(Screen.About.route)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = stringResource(R.string.menu_info)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        ScreenContent(innerPadding)
    }
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

        Text(stringResource(R.string.desc_main))

        Spacer(modifier = Modifier.height(20.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {

                TextField(
                    value = tinggi,
                    onValueChange = { tinggi = it },
                    label = { Text(stringResource(R.string.tinggi)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                TextField(
                    value = umur,
                    onValueChange = { umur = it },
                    label = { Text(stringResource(R.string.umur)) },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(stringResource(R.string.gender))

                Row(verticalAlignment = Alignment.CenterVertically) {

                    RadioButton(
                        selected = gender == "Pria",
                        onClick = { gender = "Pria" }
                    )
                    Text(stringResource(R.string.pria))

                    Spacer(modifier = Modifier.width(16.dp))

                    RadioButton(
                        selected = gender == "Wanita",
                        onClick = { gender = "Wanita" }
                    )
                    Text(stringResource(R.string.wanita))
                }
            }
        }
        val errorKosong = stringResource(R.string.error_kosong)

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                if (tinggi.isEmpty() || umur.isEmpty()) {
                    hasil = errorKosong
                } else {
                    val t = tinggi.toFloat()
                    val u = umur.toInt()
                    val ideal = hitungBeratIdeal(t, gender, u)
                    hasil = "Berat ideal: %.1f kg".format(ideal)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.hitung))
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (hasil.isNotEmpty()) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(stringResource(R.string.hasil))
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(hasil)
                }
            }
        }
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
        hasil -= 1.5f
    }

    return hasil
}