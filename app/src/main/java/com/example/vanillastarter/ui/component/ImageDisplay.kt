package com.example.vanillastarter.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil3.compose.rememberAsyncImagePainter

@Composable
fun ImageDisplay(imagePath: String?) {
    if (imagePath.isNullOrBlank()) {
        // Do nothing when imagePath is null or blank
        return
    }

    when {
        imagePath.toIntOrNull() != null -> {
            // Resource ID
                Image(
                    painter = painterResource(id = imagePath.toInt()),
                    contentDescription = "Image Display",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
        }
        else -> {
            // File Path or URL
                Image(
                    painter = rememberAsyncImagePainter(model = imagePath),
                    contentDescription = "Image Display",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxSize()
                )
        }
    }
}
