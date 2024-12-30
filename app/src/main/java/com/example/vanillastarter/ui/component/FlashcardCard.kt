package com.example.vanillastarter.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.vanillastarter.R
import com.example.vanillastarter.data.Flashcard
import com.example.vanillastarter.utils.parseColor

@Composable
fun FlashcardCard(
    flashcard: Flashcard,
    onClickFlashcard: () -> Unit,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit
) {
    var moreOptionRevealed by remember { mutableStateOf(false) }

    ElevatedCard(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickFlashcard() }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(parseColor(flashcard.backgroundColor))
                .heightIn(min = 200.dp, max = 250.dp)
        ) {
            ImageDisplay(flashcard.imagePath)

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .heightIn(min = 30.dp, max = 0.2f * 250.dp)
                    .fillMaxWidth()
                    .background(
                        if (flashcard.imagePath == null) {
                            parseColor(flashcard.backgroundColor).copy(alpha = 1.0f)
                        } else {
                            parseColor(flashcard.backgroundColor).copy(alpha = 0.7f)
                        }
                    )
            ) {
                Text(
                    text = flashcard.name,
                    style = MaterialTheme.typography.displaySmall,
                    color = Color.White
                )
            }

            IconButton(
                onClick = { moreOptionRevealed = !moreOptionRevealed },
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.TopEnd)
                    .clip(CircleShape)
                    .background(Color(red = 1.0f, green = 1.0f, blue = 1.0f, alpha = 0.6f))
                    .size(40.dp)
            ) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "More Options",
                    modifier = Modifier
                        .rotate(90f)
                        .size(40.dp)
                )
            }

            if (moreOptionRevealed) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
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
    }
}


@Preview(showBackground = false)
@Composable
fun FlashcardCardWithoutImage() {
    val example = Flashcard(
        id = 1,
        name = "Hack",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...",
        link = null,
        imagePath = null,
        frequency = 1,
        parentId = 1,
        backgroundColor = "#2E5077"
    )
    FlashcardCard(
        flashcard = example,
        onClickFlashcard = {  },
        onClickEdit = {  },
        onClickDelete = {  }
    )
}

@Preview(showBackground = false)
@Composable
fun FlashcardCardWithImage() {
    val example = Flashcard(
        id = 1,
        name = "Hack",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...",
        link = null,
        imagePath = R.drawable.card_image_example_2.toString(),
        frequency = 1,
        parentId = 1,
        backgroundColor = "#2E5077"
    )
    FlashcardCard(
        flashcard = example,
        onClickFlashcard = {  },
        onClickEdit = {  },
        onClickDelete = {  }
    )
}