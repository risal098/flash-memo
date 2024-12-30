package com.example.vanillastarter.nav
import com.example.vanillastarter.page.*
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vanillastarter.func.*
import android.content.Intent
import android.net.Uri
@Composable
fun navCenter(context: Context,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory,onPickImage: () -> Unit,
    imageUri: Uri?) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { showAllDataPage(navController, 0,0,FlashcardViewModel,CategoryViewModel,onPickImage,imageUri) 
        }
        composable("showAllDataPage/{id}/{parentId}") { navBackStackEntry ->
            val idString = navBackStackEntry.arguments?.getString("id")
   				  val id: Int? = idString?.toIntOrNull()
            val parentIdString = navBackStackEntry.arguments?.getString("parentId")
   				  val parentId: Int? = parentIdString?.toIntOrNull()
            showAllDataPage(navController, id!!,parentId!!,FlashcardViewModel,CategoryViewModel,onPickImage,imageUri) 
        }
        
   }//end list
   
   
}


