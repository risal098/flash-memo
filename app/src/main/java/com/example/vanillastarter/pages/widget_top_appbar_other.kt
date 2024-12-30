package com.example.vanillastarter.pages

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBarAppOthers(name: String){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(Icons.AutoMirrored.Rounded.KeyboardArrowLeft, contentDescription = "Localized description", modifier = Modifier.size(40.dp))
        Text(name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    }
}