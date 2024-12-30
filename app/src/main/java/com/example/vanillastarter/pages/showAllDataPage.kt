package com.example.vanillastarter.page


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
import kotlin.random.Random
import com.example.vanillastarter.data.*
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
@Composable
fun showAllDataPage(navController: NavController,selfId:Int,parentId:Int,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory,subCategory:Category?=null){

 Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp(navController, selfId,parentId,FlashcardViewModel,CategoryViewModel)
                }

}

@Composable
fun MyApp(navController: NavController,thisParentId:Int,parentId:Int,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory,subCategory:Category?=null) {
    val stringList = listOf("One", "Two", "Three", "Four", "Five")
    var numberList by remember {mutableStateOf(mutableListOf(1, 2, 3, 4, 5))}
    
    val categoryDataList by CategoryViewModel.dataList.collectAsState() //list/set category
		val flashcardDataList by FlashcardViewModel.dataList.collectAsState() // list/set flashcard
		val grandParentCategory by CategoryViewModel.category.collectAsState() // object grandparent category
		FlashcardViewModel.loadAllData(thisParentId) //render all ui and list set flash
		CategoryViewModel.loadAllData(thisParentId) //render all ui and list set category
		if(parentId!=0){
		
		CategoryViewModel.loadCategory(parentId)
		
		 
		}
    Scaffold(
    
        floatingActionButton = {
            FloatingActionButton(onClick = { CategoryViewModel.addData(name="mimi",parentId= thisParentId)} ){
                Text("+")
            }
        },
        floatingActionButtonPosition = FabPosition.End
       )  { 
       padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
        		item{
        		Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ){Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        
        if(parentId!=0){
        Button(onClick = { navController.navigate("showAllDataPage/{id}/{parentId}".replace("{id}", grandParentCategory!!.id!!.toString()).replace("{parentId}", grandParentCategory!!.parentId!!.toString())
                    
                    ) }, modifier = Modifier.weight(1f)) {
                       if (grandParentCategory!=null){Text("go back"+grandParentCategory!!.id.toString())
                       }else{ Text("go back")}
                    }//end button go back
                    }else{
                    
                      Button(onClick = { navController.navigate("home") }, modifier = Modifier.weight(1f)) {
                        Text("go home")
                    }//end button go back
                    
                    }
                    
                    
                    Button(onClick = { numberList.add(Random.nextInt(100));
       FlashcardViewModel.addData(name="sex"+Random.nextInt(100).toString(), description="argh",parentId=thisParentId) ;
       
        }, modifier = Modifier.weight(1f)) {
                        Text("add card")
                    }
        		}}}
        		
        		if(thisParentId!=0){
        		item {
                Text(
                    text = "Categorysad",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        		
        		}
        		item {
                Text(
                    text = "Category",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(categoryDataList) { item ->
                categoryBoxWithButtons(navController,item,FlashcardViewModel,CategoryViewModel,thisParentId)
                
            }

    				
           

            // Header for flashcard
            item {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "flashcard",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }

            // flashcard List
            items(flashcardDataList) { item ->
                FlashcardBoxWithButtons(navController,item,FlashcardViewModel,CategoryViewModel,thisParentId)
            }
          
            
        }
    }
}

@Composable
fun FlashcardBoxWithButtons(navController: NavController,item: Flashcard,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory,thisParentId:Int) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.name+"id="+item.id.toString()+"parent"+item.parentId.toString(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {FlashcardViewModel.updateData(item.copy(name = "sux"+Random.nextInt(100).toString()),thisParentId) }, modifier = Modifier.weight(1f)) {
                    Text("Update")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(onClick = { FlashcardViewModel.deleteData(item,thisParentId) }, modifier = Modifier.weight(1f)) {
                    Text("delete")
                }
                 
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(onClick = { /* Do nothing */ }, modifier = Modifier.weight(1f)) {
                        Text("see")
                    }
                
        }
    }
}

}



@Composable
fun categoryBoxWithButtons(navController: NavController,item: Category,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory,thisParentId:Int) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = item.name+"id="+item.id.toString()+"parent"+item.parentId.toString(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { /* Do nothing */ }, modifier = Modifier.weight(1f)) {
                    Text("Update")
                }
                Spacer(modifier = Modifier.width(4.dp))
                Button(onClick = { CategoryViewModel.deleteData(item,thisParentId) }, modifier = Modifier.weight(1f)) {
                    Text("delete")
                }
                
                    Spacer(modifier = Modifier.width(4.dp))
                    Button(onClick = {FlashcardViewModel.loadAllData(item.id!!); CategoryViewModel.loadAllData(item.id!!);navController.navigate("showAllDataPage/{id}/{parentId}".replace("{id}", item.id.toString()).replace("{parentId}", item.parentId.toString())
                    
                    ) }, modifier = Modifier.weight(1f)) {
                        Text("Go")
                    }
                
            }
        }
    }
}


