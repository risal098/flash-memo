package com.example.vanillastarter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Category)

    @Update
    suspend fun update(item: Category)

    @Delete
    suspend fun delete(item: Category)

    @Query("SELECT * from categories WHERE id = :id")
    fun getCategoryDetail(id: Int): Flow<Category>

    @Query("SELECT * from categories WHERE parentId = :parentId ORDER BY name ASC")
    fun getCategoriesSortNameAsc(parentId: Int?): Flow<List<Category>>

    @Query("SELECT * from categories WHERE parentId = :parentId ORDER BY name DESC")
    fun getCategoriesSortNameDesc(parentId: Int?): Flow<List<Category>>

    @Query("SELECT * from categories WHERE parentId = :parentId ORDER BY frequency ASC")
    fun getCategoriesSortFrequencyAsc(parentId: Int?): Flow<List<Category>>

    @Query("Select * from categories WHERE parentId = :parentId ORDER BY frequency DESC")
    fun getCategoriesSortFrequencyDesc(parentId: Int?): Flow<List<Category>>

}