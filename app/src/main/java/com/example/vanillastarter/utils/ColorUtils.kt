package com.example.vanillastarter.utils

import androidx.compose.ui.graphics.Color

fun parseColor(colorString: String, defaultColor: Color = Color.Gray): Color {
    return try {
        Color(android.graphics.Color.parseColor(colorString))
    } catch (e: IllegalArgumentException) {
        defaultColor // Fallback if the color string is invalid
    }
}
