package com.example.vanillastarter.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.vanillastarter.R
import com.example.vanillastarter.data.Category
import com.example.vanillastarter.utils.parseColor

@Composable
fun CategoryCard(
    category: Category,
    onClickCategory: () -> Unit,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit,
    onClickQuiz: () -> Unit
) {
    var moreOptionRevealed by remember { mutableStateOf(false) }

    ElevatedCard(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickCategory() }
    ) {
        Box(
            modifier = Modifier
                .background(parseColor(category.backgroundColor))
                .heightIn(min = 200.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 150.dp)
                ) {
                    ImageDisplay(category.imagePath)
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 8.dp)
                    ) {
                        Text(
                            text = category.name,
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier
                                .padding(top = 4.dp)
                        )

                        Spacer(
                            modifier = Modifier
                                .padding(10.dp)
                        )

                        category.description?.let{
                            Text(
                                text = category.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }

                        Spacer(
                            modifier = Modifier
                                .padding(10.dp)
                        )

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Button(
                                onClick = { onClickQuiz() },
                                colors = ButtonDefaults.buttonColors(parseColor("#4DA1A9")),
                                modifier = Modifier
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = "Quiz"
                                )
                            }
                        }
                    }
                }
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
fun CategoryCardWithoutImage() {
    val example = Category(
        id = 1,
        name = "Bahasa Belanda",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...",
        imagePath = null,
        frequency = 1,
        parentId = null,
        backgroundColor = "#A6D3CE"
    )
    CategoryCard(
        category = example,
        onClickCategory = {  },
        onClickEdit = {  },
        onClickDelete = {  },
        onClickQuiz = {  }
    )
}

@Preview(showBackground = false)
@Composable
fun CategoryCardWithImage() {
    val example2 = Category(
        id = 2,
        name = "Istilah IT",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...",
        imagePath = R.drawable.card_image_example.toString(),
        frequency = 1,
        parentId = null,
        backgroundColor = "#AACC99"
    )
    CategoryCard(
        category = example2,
        onClickCategory = {  },
        onClickEdit = {  },
        onClickDelete = {  },
        onClickQuiz = {  }
    )
}