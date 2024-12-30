package com.example.vanillastarter.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanillastarter.R

@Composable
fun TextFieldSet() {
    // State untuk menyimpan teks dan warna yang dipilih
    var nama by remember { mutableStateOf(TextFieldValue("")) }
    var desk by remember { mutableStateOf(TextFieldValue("")) }
    var link by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) } // Untuk dropdown menu
    var selectedColor by remember { mutableStateOf(Color(0xffF7BDD4)) } // Warna default

    // Opsi warna yang disediakan aplikasi
    val colorOptions = listOf(
        "Pink" to colorResource(R.color.pink),
        "Green" to colorResource(R.color.green),
        "Blue" to colorResource(R.color.blue),
        "Orange" to colorResource(R.color.orange),
        "Teal" to colorResource(R.color.lightTeal),
        "Light Pink" to colorResource(R.color.lightPink),
        "Purple" to colorResource(R.color.purple),

    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        AddImageBox(onClick = { }, false)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Nama Set",
            color = colorResource(R.color.greyTheme),
            )
        OutlinedTextField(
            maxLines = 1,
            value = nama,
            onValueChange = { nama = it },
//            label = { Text("Enter text") },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.backgorundOne), shape = RoundedCornerShape(15.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Deskripsi",
            color = colorResource(R.color.greyTheme),
        )
        OutlinedTextField(
            value = desk,
            onValueChange = { desk = it },
//            label = { Text("Enter text") },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.backgorundOne), shape = RoundedCornerShape(15.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Link",
            color = colorResource(R.color.greyTheme),
        )
        OutlinedTextField(
            value = link,
            maxLines = 1,
            onValueChange = { link = it },
//            label = { Text("Enter text") },
            modifier = Modifier
                .fillMaxWidth()
                .background(color = colorResource(R.color.backgorundOne), shape = RoundedCornerShape(15.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown untuk memilih warna
        Box {
            Button(
                onClick = { expanded = true },
                border = BorderStroke(
                    width = 1.dp,
                    color = colorResource(R.color.darkBlue)
                ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.backgorundOne) // Warna latar belakang button
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier.fillMaxWidth()
            ){
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "pilih warna set", color = colorResource(R.color.greyTheme))
                    Box(
                        modifier = Modifier.background(
                         selectedColor,
                            shape = RoundedCornerShape(100.dp)
                        ).size(20.dp)
                    ){

                    }
                }
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                colorOptions.forEach { (name, color) ->
                    DropdownMenuItem(
                        text = { Text(name) },
                        onClick = {
                            selectedColor = color // Mengubah warna yang dipilih
                            expanded = false // Menutup dropdown
                        },
                        modifier = Modifier
                            .background(color = color, shape = RoundedCornerShape(4.dp))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().background(
                colorResource(R.color.teal),
                shape = RoundedCornerShape(100.dp)
            ).height(60.dp)
        ){
            Text(text = "Tambah", color = Color.White, fontSize = 24.sp)
        }
    }
}

@Preview
@Composable
fun PreviewTextFieldSet() {

    TextFieldSet()
//    LayoutAddSet()
//    AddCardOrSet()
}
