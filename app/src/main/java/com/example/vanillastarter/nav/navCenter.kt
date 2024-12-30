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
import com.example.vanillastarter.pages.*
@Composable
fun navCenter(context: Context,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory,onPickImage: () -> Unit,
    imageUri: Uri?) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") 
        //{Layout(true,"risal")}
        { Layout(navController, 0,0,FlashcardViewModel,CategoryViewModel,onPickImage,imageUri)  }
        composable("showAllDataPage/{thisParentId}/{grandParentId}") { navBackStackEntry ->
            val idString = navBackStackEntry.arguments?.getString("thisParentId")
   				  val id: Int? = idString?.toIntOrNull()
            val parentIdString = navBackStackEntry.arguments?.getString("grandParentId")
   				  val parentId: Int? = parentIdString?.toIntOrNull()
            Layout(navController, id!!,parentId!!,FlashcardViewModel,CategoryViewModel,onPickImage,imageUri) 
        }
        composable("addFlashcard/{thisParentId}/{grandParentId}"){navBackStackEntry ->
        		val idString = navBackStackEntry.arguments?.getString("thisParentId")
   				  val id: Int? = idString?.toIntOrNull()
            val parentIdString = navBackStackEntry.arguments?.getString("grandParentId")
   				  val parentId: Int? = parentIdString?.toIntOrNull()
   				  LayoutAddCard(id!!,parentId!!)
        }
        composable("addCategory/{thisParentId}/{grandParentId}"){navBackStackEntry ->
        		val idString = navBackStackEntry.arguments?.getString("thisParentId")
   				  val id: Int? = idString?.toIntOrNull()
            val parentIdString = navBackStackEntry.arguments?.getString("grandParentId")
   				  val parentId: Int? = parentIdString?.toIntOrNull()
   				  LayoutAddSet(id!!,parentId!!)
        }
   }//end list
   
   
}


