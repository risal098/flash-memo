package com.example.vanillastarter.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category (
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val description: String,
    val imagePath: String?,
    val frequency: Int,
    val parentId: Int?,
    val backgroundColor: String
)
