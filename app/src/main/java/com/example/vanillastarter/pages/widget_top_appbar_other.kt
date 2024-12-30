package com.example.vanillastarter.pages
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
@Composable
fun TopBarAppOthers(name: String,parentId:Int,grandParentId:Int,navController:NavController){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(Icons.AutoMirrored.Rounded.KeyboardArrowLeft, contentDescription = "Localized description", modifier = Modifier.size(40.dp).clickable{navController.navigate("showAllDataPage/{thisParentId}/{grandParentId}".replace("{thisParentId}", parentId.toString()).replace("{grandParentId}", grandParentId.toString()))}
        )
        Text(name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    }
}
