package com.example.vanillastarter.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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


import androidx.compose.ui.draw.clip


import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanillastarter.data.Category
import com.example.vanillastarter.shared.SortType
import com.example.vanillastarter.ui.component.CategoryBanner
import com.example.vanillastarter.ui.component.SearchBar
import com.example.vanillastarter.ui.component.SortButton
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.activity.viewModels
import androidx.lifecycle.viewModelScope 
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.* // For Modifier, dp, etc.
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text // For Text composable, if used
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.vanillastarter.func.*
import com.example.vanillastarter.data.*
import kotlin.random.Random
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*



import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.text.style.TextAlign
import coil.compose.rememberImagePainter
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
                color = colorResource(colorDict("teal")),
                shape = RoundedCornerShape(100.dp)
            )
            .padding(20.dp)
    ){
        Icon(Icons.Default.Add, contentDescription = "Localized description", modifier = Modifier.size(30.dp),
            tint = colorResource(R.color.white)
        )
    }
}

//first setname
//ini page nya
@Composable
fun Layout(navController: NavController,thisParentId:Int,parentId:Int,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory,onPickImage: () -> Unit,imageUri: Uri?,subCategory:Category?=null){
  val context = LocalContext.current
    val categoryDataList by CategoryViewModel.dataList.collectAsState() //list/set category
		val flashcardDataList by FlashcardViewModel.dataList.collectAsState() // list/set flashcard
		val grandParentCategory by CategoryViewModel.category.collectAsState() // object grandparent category
		val thisCategory by CategoryViewModel.thisCategory.collectAsState()
     

		if(parentId!=0){

		CategoryViewModel.loadCategory(parentId)

		 }else{
		 CategoryViewModel.nullCategory()
		 }
		 if(thisParentId!=0){

		CategoryViewModel.loadThisCategory(thisParentId)

		 }else{
		 CategoryViewModel.nullThisCategory()
		 }
    val example = Category(
        id = 1,
        name = "Bahasa Belanda",
        description = "Loading...",
        imagePath = R.drawable.card_image_example.toString(),
        frequency = 1,
        parentId = null,
        backgroundColor = "#A6D3CE"
    )
    var searchQuery by remember { mutableStateOf("") }
    val isAdding = remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var selectedSortType by remember { mutableStateOf(SortType.NAME_ASC) }

    // Trigger loading whenever `selectedSortType` changes
    LaunchedEffect(selectedSortType) {
        Log.d("Layout", "Data List before Sort: $flashcardDataList")

        FlashcardViewModel.loadDataBySort(thisParentId, selectedSortType)
        CategoryViewModel.loadDataBySort(thisParentId, selectedSortType)
    }

    // Filter based on the search query
    val filteredCategories = remember(searchQuery, categoryDataList) {
        categoryDataList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }
    val filteredFlashcards = remember(searchQuery, flashcardDataList) {
        flashcardDataList.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                return Offset.Zero
            }
        }
    }

    Scaffold (
        containerColor = colorResource(R.color.backgorundOne),


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
            Column(
                modifier = Modifier
                    .width(screenWidth)
                    .nestedScroll(nestedScrollConnection)
                    .verticalScroll(rememberScrollState())
                    .background(
                        color = colorResource(R.color.backgorundOne)
                    )


            ) {
                if(thisParentId==0){
                    TopBarAppFirst("Home", R.drawable.androidparty)
                } else{
                    if(grandParentCategory!=null){
                        TopBarAppOthers("Back",parentId,grandParentCategory!!.parentId!!,navController)
                    }else{
                        TopBarAppOthers("Go home",0,0,navController)
                    }
//                            TopBarAppOthers(grandParentCategory!!.name)
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
                if(thisCategory!=null){
                    CategoryBanner(
                        category = thisCategory!!,
                        onClickPlay = {  }
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                if(filteredCategories.size==0&&filteredFlashcards.size==0){
                    Column {
                        Image(
                            painter = painterResource(id = R.drawable.empty_box),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
//                alpha = 1F,
                            modifier = Modifier
                                .height(200.dp).fillMaxWidth()
                                .align(alignment = Alignment.CenterHorizontally)
                        )
                        Text("Belum ada data yang ditambahkan", textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth().align(alignment = Alignment.CenterHorizontally))
                    }
                }else {
                    if (filteredCategories.size != 0) {
                        Column {
                            SubJudul("Set Kartu")
                            Spacer(modifier = Modifier.height(10.dp))
                            ResponsiveGridLayout(
                                filteredCategories,
                                thisParentId,
                                parentId,
                                navController,
                                FlashcardViewModel,
                                CategoryViewModel
                            )
                        }
                    }

                    if(filteredFlashcards.size!=0){
                        Column {
                            Spacer(modifier = Modifier.height(20.dp))
                            SubJudul("Kartu")
                            Spacer(modifier = Modifier.height(10.dp))
                            CardLayout(navController,filteredFlashcards,thisParentId,parentId,FlashcardViewModel ,CategoryViewModel)
                        }
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
                    AddCardOrSet(navController,thisParentId,thisParentId,parentId)
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






