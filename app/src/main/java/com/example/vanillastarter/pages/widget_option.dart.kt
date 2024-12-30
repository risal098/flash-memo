package com.example.vanillastarter.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Option1(
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit// Default alignment
) {
    var moreOptionRevealed by remember { mutableStateOf(false) }
    IconButton(
        onClick = { moreOptionRevealed = !moreOptionRevealed },
        modifier = Modifier
            .padding(8.dp)
            .clip(CircleShape)
            .background(Color(red = 1.0f, green = 1.0f, blue = 1.0f, alpha = 0.6f))
            .size(25.dp)
    ) {
        Icon(
            Icons.Default.MoreVert,
            contentDescription = "More Options",
            modifier = Modifier
                .rotate(90f)
                .size(25.dp)
        )
    }

    if (moreOptionRevealed) {
        Column(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
        ) {
            Spacer(modifier = Modifier.height(1.dp))
            TextButton(onClick = { onClickEdit(); moreOptionRevealed = false }) {
                Text(text = "Edit")
            }
            TextButton(onClick = { onClickDelete(); moreOptionRevealed = false }) {
                Text(text = "Delete")
            }
        }
    }
}
