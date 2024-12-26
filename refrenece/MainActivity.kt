
package com.example.vanillastarter
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
@Entity
data class DataEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val randomNumber: Int
)

@Dao
interface DataDao {
    @Query("SELECT * FROM DataEntity")
    suspend fun getAllData(): List<DataEntity>

    @Insert
    suspend fun insertData(data: DataEntity)

    @Update
    suspend fun updateData(data: DataEntity)

    @Delete
    suspend fun deleteData(data: DataEntity)
}
@Database(entities = [DataEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao
}

class DataViewModel(private val dao: DataDao) : ViewModel() {
    private val _dataList = MutableStateFlow<List<DataEntity>>(emptyList())
    val dataList: StateFlow<List<DataEntity>> get() = _dataList

    init {
        loadAllData()
    }

    private fun loadAllData() {
        viewModelScope.launch {
            _dataList.value = dao.getAllData()
        }
    }

    fun addData(name: String, randomNumber: Int) {
        viewModelScope.launch {
            dao.insertData(DataEntity(name = name, randomNumber = randomNumber))
            loadAllData()
        }
    }

    fun updateData(data: DataEntity) {
        viewModelScope.launch {
            dao.updateData(data)
            loadAllData()
        }
    }

    fun deleteData(data: DataEntity) {
        viewModelScope.launch {
            dao.deleteData(data)
            loadAllData()
        }
    }
}

class DataViewModelFactory(private val dao: DataDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DataViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DataViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun DataApp(viewModel: DataViewModel) {
    val dataList by viewModel.dataList.collectAsState()
    var name by remember { mutableStateOf("") }
    var randomNumber by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            BasicTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.weight(1f),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (name.isEmpty()) Text("Name")
                    innerTextField()
                }
            )
            BasicTextField(
                value = randomNumber,
                onValueChange = { randomNumber = it },
                modifier = Modifier.weight(1f),
                singleLine = true,
                decorationBox = { innerTextField ->
                    if (randomNumber.isEmpty()) Text("Random Number")
                    innerTextField()
                }
            )
            Button(onClick = {
                val number = randomNumber.toIntOrNull()
                if (!name.isBlank() && number != null) {
                    viewModel.addData(name, number)
                    name = ""
                    randomNumber = ""
                }
            }) {
                Text("Add")
            }
        }

        Divider()

        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items(dataList) { data ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("${data.name} - ${data.randomNumber}")
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Button(onClick = {
                            val updatedData = data.copy(name = name.ifEmpty { data.name }, randomNumber = randomNumber.toIntOrNull() ?: data.randomNumber)
                            viewModel.updateData(updatedData)
                        }) {
                            Text("Edit")
                        }
                        Button(onClick = { viewModel.deleteData(data) }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }
    }
}

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


/*
package com.example.vanillastarter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vanillastarter.ui.theme.VanillastarterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VanillastarterTheme {
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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello funcking shit $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VanillastarterTheme {
        Greeting("Android")
    }
}
*/
