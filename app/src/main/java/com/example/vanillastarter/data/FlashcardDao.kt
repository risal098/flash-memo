package com.example.vanillastarter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Flashcard)

    @Update
    suspend fun update(item: Flashcard)

    @Delete
    suspend fun delete(item: Flashcard)

    @Query("SELECT * from flashcards WHERE id = :id")
    fun getFlashcardDetail(id: Int): Flow<Flashcard>

    @Query("SELECT * from flashcards WHERE parentId = :parentId ORDER BY name ASC")
    fun getFlashcardsSortNameAsc(parentId: Int?): Flow<List<Flashcard>>

    @Query("SELECT * from flashcards WHERE parentId = :parentId ORDER BY name DESC")
    fun getFlashcardsSortNameDesc(parentId: Int?): Flow<List<Flashcard>>

    @Query("SELECT * from flashcards WHERE parentId = :parentId ORDER BY frequency ASC")
    fun getFlashcardsSortFrequencyAsc(parentId: Int?): Flow<List<Flashcard>>

    @Query("Select * from flashcards WHERE parentId = :parentId ORDER BY frequency DESC")
    fun getFlashcardsSortFrequencyDesc(parentId: Int?): Flow<List<Flashcard>>

}