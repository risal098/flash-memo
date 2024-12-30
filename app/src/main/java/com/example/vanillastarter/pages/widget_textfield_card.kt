package com.example.vanillastarter.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanillastarter.R
import androidx.navigation.NavController
import com.example.vanillastarter.func.*
import com.example.vanillastarter.data.*
import android.net.Uri
@Composable
fun TextFieldCard(navController: NavController,thisParentId:Int,parentId:Int,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory,onPickImage: () -> Unit,imageUri: Uri?,subCategory:Category?=null){
    var nama by remember { mutableStateOf(TextFieldValue("")) }
    var desk by remember { mutableStateOf(TextFieldValue("")) }
    var link by remember { mutableStateOf(TextFieldValue("")) }
    var expanded by remember { mutableStateOf(false) } // Untuk dropdown menu


    Column(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        AddImageBox(onClick = { }, false)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Judul",
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


        Spacer(modifier = Modifier.height(16.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().background(
                colorResource(R.color.teal),
                shape = RoundedCornerShape(100.dp)
            ).height(60.dp)
                .clickable { 
                FlashcardViewModel.addData(name=nama.text, description=desk.text,parentId=thisParentId,link=link.text)
                navController.navigate("showAllDataPage/{thisParentId}/{grandParentId}".replace("{thisParentId}", thisParentId.toString()).replace("{grandParentId}", parentId.toString())) }
        ){
            Text(text = "Tambah", color = Color.White, fontSize = 24.sp)
        }
    }
}
