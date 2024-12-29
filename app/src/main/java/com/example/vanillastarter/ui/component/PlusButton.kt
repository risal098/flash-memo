package com.example.vanillastarter.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vanillastarter.utils.parseColor

@Composable
fun PlusButton(
    parentId: Int?,
    onClickAddCategory: () -> Unit,
    onClickAddFlashcard: () -> Unit
) {
    // Track whether the add button menu is revealed
    var plusButtonExpanded by remember { mutableStateOf(false) }

    if (parentId == null) {
        // Single FAB for adding a category when parentId is null
        FloatingActionButton(
            onClick = onClickAddCategory,
            containerColor = parseColor("#44A088")
        ) {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Add Category",
                tint = Color.White
            )
        }
    } else {
        // FAB menu when parentId is not null
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            // Add Flashcard button
            AnimatedVisibility(
                visible = plusButtonExpanded,
                enter = slideInVertically(initialOffsetY = { it + 100 }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it + 100 }) + fadeOut()
            ) {
                FloatingActionButton(
                    onClick = onClickAddFlashcard,
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(Icons.Filled.Create, contentDescription = "Add Flashcard")
                }
            }

            // Add Category button
            AnimatedVisibility(
                visible = plusButtonExpanded,
                enter = slideInVertically(initialOffsetY = { it + 50 }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it + 50 }) + fadeOut()
            ) {
                FloatingActionButton(
                    onClick = onClickAddCategory,
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Icon(Icons.Filled.Email, contentDescription = "Add Category")
                }
            }

            // Main button to toggle visibility
            FloatingActionButton(
                onClick = { plusButtonExpanded = !plusButtonExpanded },
                containerColor = parseColor("#44A088")
            ) {
                Icon(
                    imageVector = if (plusButtonExpanded) Icons.Filled.Close else Icons.Filled.Add,
                    contentDescription = if (plusButtonExpanded) "Close Add Menu" else "Open Add Menu",
                    tint = Color.White
                )
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
fun PlusButtonWithoutParent() {
    MaterialTheme {
        PlusButton(
            parentId = null,
            onClickAddCategory = {  },
            onClickAddFlashcard = {  }
        )
    }
}

@Preview(showBackground = false)
@Composable
fun PlusButtonWithParent() {
    MaterialTheme {
        PlusButton(
            parentId = 1,
            onClickAddCategory = {  },
            onClickAddFlashcard = {  }
        )
    }
}
