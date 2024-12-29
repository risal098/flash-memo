package com.example.vanillastarter.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Category::class, Flashcard::class], version = 2, exportSchema = false)
abstract class FlashMemoDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun flashcardDao(): FlashcardDao

    companion object {
        @Volatile
        private var Instance: FlashMemoDatabase? = null

        fun getDatabase(context: Context): FlashMemoDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FlashMemoDatabase::class.java, "flashmemo_database").allowMainThreadQueries()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
