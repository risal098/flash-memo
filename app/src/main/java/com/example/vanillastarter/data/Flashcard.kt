package com.example.vanillastarter.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(tableName = "flashcards")
data class Flashcard (
    @PrimaryKey(autoGenerate = true) 
    val id:  Int? = null ,
    val name: String,
    val description: String,
    val link: String?,
    val imagePath: String?,
    val frequency: Int,
    val parentId: Int,
    val backgroundColor: String,
)
