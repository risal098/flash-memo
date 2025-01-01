package com.example.vanillastarter.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vanillastarter.R
import com.example.vanillastarter.data.Category
import com.example.vanillastarter.utils.parseColor
import com.example.vanillastarter.data.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@Composable
fun CategoryBanner(
    category: Category,
    onClickPlay: () -> Unit
) {
    ElevatedCard(
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
//                .background(color=parseColor(category.backgroundColor))
                .background(color = colorResource(colorDict(category.backgroundColor)))
                .heightIn(min = 130.dp)
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
                            .padding(start = 16.dp, top = 12.dp, end = 16.dp, bottom = 16.dp)
                    ) {
                        category.description?.let{
                            Text(
                                text = category.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Button(
                        onClick = { onClickPlay() },
                        colors = ButtonDefaults.buttonColors(parseColor("#4DA1A9")),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Play"
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
fun CategoryBannerWithoutImage() {
    val example = Category(
        id = 1,
        name = "Bahasa Belanda",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...",
        imagePath = null,
        frequency = 1,
        parentId = null,
        backgroundColor = "#A6D3CE"
    )
    CategoryBanner(
        category = example,
        onClickPlay = {  }
    )
}

@Preview(showBackground = false)
@Composable
fun CategoryBannerWithImage() {
    val example2 = Category(
        id = 2,
        name = "Istilah IT",
        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...",
        imagePath = R.drawable.card_image_example.toString(),
        frequency = 1,
        parentId = null,
        backgroundColor = "#AACC99"
    )
    CategoryBanner(
        category = example2,
        onClickPlay = {  }
    )
}
