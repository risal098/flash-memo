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
import com.example.vanillastarter.data.Category
import com.example.vanillastarter.shared.SortType
import com.example.vanillastarter.ui.component.CategoryBanner
import com.example.vanillastarter.ui.component.SearchBar
import com.example.vanillastarter.ui.component.SortButton

//ini widget2, page ada di paling bawah
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


//ini page nya
@Composable
fun Layout(first: Boolean, setName: String = ""){
    val example = Category(
        id = 1,
        name = "Bahasa Belanda",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...",
        imagePath = R.drawable.card_image_example.toString(),
        frequency = 1,
        parentId = null,
        backgroundColor = "#A6D3CE"
    )
    var searchQuery = ""
    val isAdding = remember { mutableStateOf(false) }
    val items = listOf(
        listOf(R.drawable.androidparty, "Judul 1", "Loremdo eiusmem ipsum dolor sit asmem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...", R.color.lightPink ),
        listOf(R.drawable.androidparty, "Ini Judul 2", "Loremdo eiusmem ipsum dolor sit asmem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...", R.color.purple),
        listOf(0, "Ini Judul 2", "Loremdo eiusmem ipsum dolor sit asmem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...", R.color.blue)
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var selectedSortType = SortType.NAME_ASC
    Scaffold (
        containerColor = colorResource(R.color.backgorundOne)
    ){

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
                        SearchBar(
                            searchQuery = searchQuery,
                            onInput = { searchQuery = it }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
//                        FilterBox(modifier = Modifier)


                        SortButton(
                            onSortTypeSelected = { selectedSortType = it }
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        if(first!=true){
                            CategoryBanner(
                                category = example,
                                onClickPlay = {  }
                            )
                        }
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
