package com.example.vanillastarter.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextOverflow
import com.example.vanillastarter.R
import com.example.vanillastarter.func.*
import com.example.vanillastarter.data.*
@Composable
fun SmallButton(color: Int, text: String){
    Box(
        modifier = Modifier
            .background(
                color = colorResource(color),
                shape = RoundedCornerShape(100.dp)
            ).padding(vertical = 5.dp)
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = colorResource(R.color.white),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun CustomBox(imageRes: Int?, title: String, description: String, modifier: Modifier = Modifier, color: Int) {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = modifier.background(
            color = colorResource(color),
            shape = RoundedCornerShape(20.dp)
        )

    ){
        Column(

        ) {
            // Gambar dari drawable lokal
            if(imageRes != 0){
                Image(
                    painter = painterResource(id = imageRes!!),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
//                alpha = 1F,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                )
            }else{
                Spacer(Modifier.height(28.dp))
            }

            Column (
                modifier = Modifier.padding(horizontal = 10.dp)
            ){
                Spacer(modifier = Modifier.height(8.dp))

                // Judul
                Text(
                    text = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(1.dp))

                // Deskripsi
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(R.color.greyTheme),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    modifier = if (imageRes != 0) {
                        Modifier.fillMaxWidth().height(100.dp)
                    } else {
                        Modifier.fillMaxWidth().height(170.dp)
                    }
                )

                Spacer(modifier = Modifier.height(5.dp))
                SmallButton(color = R.color.darkBlue, text = "Tambah Kartu")
                Spacer(modifier = Modifier.height(5.dp))
                SmallButton(color = R.color.teal, text = "Lihat Detail")

            }


        }

        Box(
            modifier = Modifier.padding(3.dp)
        ){
            Option1(onClickEdit = {  },
                onClickDelete = {  })
        }
    }
}

@Composable
fun ResponsiveGridLayout(items: List<Category>) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = (screenWidth / 2) - 25.dp

    Column(modifier = Modifier.fillMaxWidth()) {
        // Membagi item menjadi dua per baris (chunked(2))
        val chunkedItems = items.chunked(2)

        chunkedItems.forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp), // optional padding
                horizontalArrangement = Arrangement.spacedBy(10.dp) // Spasi antar item
            ) {
                rowItems.forEach { item ->
                    CustomBox(
                        imageRes = 0,
                        title = item.name,
                        description = item.description,
                        modifier = Modifier
                            .width(itemWidth)
                            .height(305.dp),
                        color = R.color.blue
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp)) // Spacer after each row
        }
    }
}
/*
@Preview
@Composable
fun PreviewResponsiveGridLayout() {
    val items = listOf(
        listOf(R.drawable.androidparty, "Judul 1", "Loremdo eiusmem ipsum dolor sit asmem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...", R.color.lightPink ),
        listOf(R.drawable.androidparty, "Ini Judul 2", "Loremdo eiusmem ipsum dolor sit asmem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...", R.color.purple),
        listOf(0, "Ini Judul 2", "Loremdo eiusmem ipsum dolor sit asmem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore...", R.color.blue)
    )
    ResponsiveGridLayout(items)
}

*/
