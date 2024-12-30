package com.example.vanillastarter.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.vanillastarter.R

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Create
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//ini widget2, page ada di paling bawah


@Composable
fun TopBarAppFirst(name: String, image: Int) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ){
        Text("Hello $name!",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold)
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
//                alpha = 0F,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(100.dp))
        )
    }
}

@Composable
fun TopBarAppOthers(name: String){
    Row (
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(Icons.AutoMirrored.Rounded.KeyboardArrowLeft, contentDescription = "Localized description", modifier = Modifier.size(40.dp))
        Text(name, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun FilterBox(modifier: Modifier){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.teal),
                shape = RoundedCornerShape(100.dp)
            )
            .padding(vertical = 5.dp, horizontal = 20.dp)
    ){
        Text("Filter", color = colorResource(R.color.white))
        Image(
            painter = painterResource(id = R.drawable.green_filter),
            contentDescription = null,
            contentScale = ContentScale.Crop,
//                alpha = 0F,
            modifier = Modifier
                .size(20.dp)
        )

    }
}

@Composable
fun SubJudul(text: String){
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ButtonAdd(){
    Box(
        modifier = Modifier
            .background(
                color = colorResource(R.color.teal),
                shape = RoundedCornerShape(100.dp)
            )
            .padding(20.dp)
    ){
        Icon(Icons.Default.Add, contentDescription = "Localized description", modifier = Modifier.size(30.dp),
            tint = colorResource(R.color.white)
        )
    }
}

@Composable
fun AddCardOrSet(){
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(bottom = 72.dp)
    ) {
        Button(
            onClick = { /* Action: Tambah Kartu */ },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.darkBlue))
        ) {
            Text(text = "Tambah Kartu", fontSize = 16.sp, color = colorResource(id = R.color.white))
        }
        Button(
            onClick = { /* Action: Tambah Set */ },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.teal))
        ) {
            Text(text = "Tambah Set", fontSize = 16.sp, color = colorResource(id = R.color.white))
        }
    }
}
//ini page nya
@Composable
fun Layout(first: Boolean, setName: String = ""){
    val isAdding = remember { mutableStateOf(false) }
    val items = listOf(
        listOf(R.drawable.androidparty, "Judul 1", "Loremdo eiusmem ipsum dolor sit asmem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...", R.color.lightPink ),
        listOf(R.drawable.androidparty, "Ini Judul 2", "Loremdo eiusmem ipsum dolor sit asmem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...", R.color.purple),
        listOf(0, "Ini Judul 2", "Loremdo eiusmem ipsum dolor sit asmem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...", R.color.blue)
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Scaffold {
        padding ->
        Box(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize()
                .background(color = colorResource(R.color.backgorundOne))
                .then(
                    if (isAdding.value) {
                        Modifier.clickable { isAdding.value = false }
                    } else {
                        Modifier // Tidak menambahkan modifier jika `isAdding` bernilai false
                    }
                )
        ){
//Belum bisa disdiscroll
            LazyColumn {
                item {
                    Column(
                        modifier = Modifier
                            .width(screenWidth)
                            .background(
                                color = colorResource(R.color.backgorundOne)
                            )


                    ) {
                        if(first){
                            TopBarAppFirst("Lorem", R.drawable.androidparty)
                        } else{
                            TopBarAppOthers(setName)
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        FilterBox(modifier = Modifier)
                        Spacer(modifier = Modifier.height(20.dp))
                        SubJudul("Set Kartu")
                        Spacer(modifier = Modifier.height(10.dp))
                        ResponsiveGridLayout(items)
                        Spacer(modifier = Modifier.height(20.dp))
                        SubJudul("Kartu")
                        Spacer(modifier = Modifier.height(10.dp))
                        CardLayout(items)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(
                        alignment = Alignment.BottomEnd
                    )
                    .padding(30.dp)
            ){
                if (isAdding.value) {
                    AddCardOrSet()
                } else {
                    Box(
                        modifier = Modifier
                            .clickable { isAdding.value = true }
                    ) {
                        ButtonAdd()
                    }
                }}
        }
    }
}





@Preview
@Composable
fun PreviewPageAllData() {

    Layout(first = false, "tech")
//    AddCardOrSet()
}
