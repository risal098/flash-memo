package com.example.vanillastarter
//import com.example.vanillastarter.data
import android.os.Bundle
import androidx.activity.ComponentActivity
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
import androidx.compose.runtime.Composable // If you are using composables in MainActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import com.example.vanillastarter.data.*
import com.example.vanillastarter.func.*
import com.example.vanillastarter.nav.navCenter
/*
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
        val viewModel: DataViewModel by viewModels { DataViewModelFactory(db.dataDao()) }

        setContent {
            MaterialTheme {
                DataApp(viewModel)
            }
        }
    }
}

*/
/*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         val database = FlashMemoDatabase.getDatabase(applicationContext)
        setContent {
            MaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}
*/

/*
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app-database"
        ).build()
        val viewModel: DataViewModel by viewModels { DataViewModelFactory(db.dataDao()) }

        setContent {
            MaterialTheme {
                DataApp(viewModel)
            }
        }
    }
}
*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = FlashMemoDatabase.getDatabase(applicationContext)

        val flashcardViewModelFactory = FlashcardViewModelFactory(database.flashcardDao())
        val categoryViewModelFactory = CategoryViewModelFactory(database.categoryDao())

        setContent {
            MaterialTheme {
                navCenter(
                		context=this,
                    FlashcardViewModel = viewModel(factory = flashcardViewModelFactory),
                    CategoryViewModel = viewModel(factory = categoryViewModelFactory)
                )
            }
        }
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello arggelarhhh $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MaterialTheme {
        Greeting("Android")
    }
}
