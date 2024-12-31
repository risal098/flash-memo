package com.example.vanillastarter.func
import com.example.vanillastarter.data.FlashcardDao 
import com.example.vanillastarter.data.Flashcard
import com.example.vanillastarter.data.CategoryDao 
import com.example.vanillastarter.data.Category
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.compose.ui.unit.dp
import com.example.vanillastarter.shared.SortType
import kotlinx.coroutines.flow.*


class crudFlashcard(private val dao: FlashcardDao) : ViewModel() {
    private val _dataList = MutableStateFlow<List<Flashcard>>(emptyList())
    val dataList: StateFlow<List<Flashcard>> get() = _dataList
    private val _flashcard = MutableStateFlow<Flashcard?>(null) // Store a single Flashcard, initially null
    val flashcard: StateFlow<Flashcard?> get()= _flashcard // Expose as read-only StateFlow

    init {
        loadAllData(0)
    }

    fun loadAllData(parentId: Int) {
        viewModelScope.launch {
            _dataList.value = dao.getFlashcardsSortNameAsc(parentId)
        }
    }
    fun loadFlashcard(id: Int) {
        viewModelScope.launch {
            _flashcard.value = dao.getFlashcardDetail(id)
        }
    }

    fun addData(name: String, description: String="",imagePath: String?=null,frequency: Int=0,parentId: Int,backgroundColor: String="",link:String?=null ) {
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

    fun loadDataBySort(parentId: Int, sortType: SortType) {
        viewModelScope.launch {
            val sortedList = when (sortType) {
                SortType.NAME_ASC -> dao.getFlashcardsSortNameAsc(parentId)
                SortType.NAME_DESC -> dao.getFlashcardsSortNameDesc(parentId)
                SortType.FREQUENCY_ASC -> dao.getFlashcardsSortFrequencyAsc(parentId)
                SortType.FREQUENCY_DESC -> dao.getFlashcardsSortFrequencyDesc(parentId)
            }
            Log.d("ViewModel", "sortedList = $sortedList")

            _dataList.value = sortedList
        }
    }
}







class crudCategory(private val dao: CategoryDao) : ViewModel() {
    private val _dataList = MutableStateFlow<List<Category>>(emptyList())
    val dataList: StateFlow<List<Category>> get() = _dataList.asStateFlow()
    private val _category = MutableStateFlow<Category?>(null) // Store a single Flashcard, initially null
    val category: StateFlow<Category?> get()= _category.asStateFlow() // Expose as read-only StateFlow

    init {
        loadAllData(0)
    }

    fun loadAllData(parentId: Int) {
        viewModelScope.launch {
            _dataList.value = dao.getCategoriesSortNameAsc(parentId)
        }
    }
    fun loadCategory(id: Int) {
        viewModelScope.launch {
            _category.value = dao.getCategoryDetail(id)
        }
    }
    
/* val id: Int = 0,
    val name: String,
    val description: String,
    val imagePath: String?,
    val frequency: Int,
    val parentId: Int?,
    val backgroundColor: String*/
    fun addData(name: String, description: String="",imagePath: String?=null,frequency: Int=0,parentId: Int,backgroundColor: String="" ) {
        viewModelScope.launch {
            dao.insert(Category(name=name, description=description,imagePath=imagePath,frequency=frequency,parentId=parentId,backgroundColor=backgroundColor))
            loadAllData(parentId)
        }
    }
    fun readData(id:Int) {
        viewModelScope.launch {
            loadCategory(id)
            
        }
    }

    fun updateData(data: Category,parentId:Int) {
        viewModelScope.launch {
            dao.update(data)
            loadAllData(parentId)
        }
    }

    fun deleteData(data: Category,parentId:Int) {
        viewModelScope.launch {
        		dao.deleteAllChildren(data.id!!)
            dao.delete(data)
            
            loadAllData(parentId)
        }
    }

    fun loadDataBySort(parentId: Int, sortType: SortType) {
        viewModelScope.launch {
            val sortedList= when (sortType) {
                SortType.NAME_ASC -> dao.getCategoriesSortNameAsc(parentId)
                SortType.NAME_DESC -> dao.getCategoriesSortNameDesc(parentId)
                SortType.FREQUENCY_ASC -> dao.getCategoriesSortFrequencyAsc(parentId)
                SortType.FREQUENCY_DESC -> dao.getCategoriesSortFrequencyDesc(parentId)
            }
            _dataList.value = sortedList
        }
    }
}


class FlashcardViewModelFactory(private val dao: FlashcardDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(crudFlashcard::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return crudFlashcard(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


class CategoryViewModelFactory(private val dao: CategoryDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(crudCategory::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return crudCategory(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

