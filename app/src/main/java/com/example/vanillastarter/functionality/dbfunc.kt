package com.example.vanillastarter.func
import com.example.vanillastarter.data.FlashcardDao 
import com.example.vanillastarter.data.Flashcard
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



class crudFlashcard(private val dao: FlashcardDao) : ViewModel() {
    private val _dataList = MutableStateFlow<List<Flashcard>>(emptyList())
    val dataList: StateFlow<List<Flashcard>> get() = _dataList
    private val _flashcard = MutableStateFlow<Flashcard?>(null) // Store a single Flashcard, initially null
    val flashcard: StateFlow<Flashcard?> get()= _flashcard // Expose as read-only StateFlow

    init {
        loadAllData(0)
    }

    private fun loadAllData(parentId: Int) {
        viewModelScope.launch {
            _dataList.value = dao.getFlashcardsSortNameAsc(parentId)
        }
    }
    private fun loadFlashcard(id: Int) {
        viewModelScope.launch {
            _flashcard.value = dao.getFlashcardDetail(id)
        }
    }
/*val name: String,
    val description: String,
    val link: String?,
    val imagePath: String?,
    val frequency: Int,
    val parentId: Int,
    val backgroundColor: String,*/
    fun addData(name: String, description: String="",imagePath: String?,frequency: Int=0,parentId: Int,backgroundColor: String="",link:String? ) {
        viewModelScope.launch {
            dao.insert(Flashcard(name=name, description=description,imagePath=imagePath,frequency=frequency,parentId=parentId,link=link,backgroundColor=backgroundColor))
            loadAllData(parentId)
        }
    }
    fun readData(id:Int) {
        viewModelScope.launch {
            loadFlashcard(id)
            
        }
    }

    fun updateData(data: Flashcard,parentId:Int) {
        viewModelScope.launch {
            dao.update(data)
            loadAllData(parentId)
        }
    }

    fun deleteData(data: Flashcard,parentId:Int) {
        viewModelScope.launch {
            dao.delete(data)
            loadAllData(parentId)
        }
    }
}

