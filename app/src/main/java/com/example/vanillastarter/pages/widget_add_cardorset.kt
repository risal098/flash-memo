package com.example.vanillastarter.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanillastarter.R
import com.example.vanillastarter.func.*
import com.example.vanillastarter.data.*
import androidx.navigation.NavController

@Composable
fun AddCardOrSet(navController: NavController,thisParentId:Int,parentId:Int,grandParentId:Int){
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 72.dp)
    ) {
        Button(
            onClick = { navController.navigate("addFlashcard/{thisParentId}/{grandParentId}/{grandgrandParentId}".replace("{thisParentId}", thisParentId.toString()).replace("{grandParentId}", parentId.toString()).replace("{grandgrandParentId}", grandParentId.toString()))  },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue))
        ) {
            Text(text = "Tambah Kartu", fontSize = 16.sp, color = colorResource(id = R.color.white))
        }
        Button(
            onClick = { navController.navigate("addCategory/{thisParentId}/{grandParentId}".replace("{thisParentId}", thisParentId.toString()).replace("{grandParentId}", parentId.toString()))  },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal))
        ) {
            Text(text = "Tambah Set", fontSize = 16.sp, color = colorResource(id = R.color.white))
        }
    }
}



