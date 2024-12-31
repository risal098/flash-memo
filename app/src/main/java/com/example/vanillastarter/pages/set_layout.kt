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
import androidx.navigation.NavController
import androidx.compose.foundation.clickable
import coil3.compose.rememberAsyncImagePainter

@Composable
fun SmallButton(color: Int, text: String,thisId:Int,thisParentId:Int,parentId:Int,navController: NavController,mode:Int){
    Box(
        modifier = Modifier
            .background(
                color = colorResource(color),
                shape = RoundedCornerShape(100.dp)
            ).padding(vertical = 5.dp)
            .clickable { if(mode==0)
            							{navController.navigate("addFlashcard/{thisParentId}/{grandParentId}/{grandgrandParentId}".replace("{thisParentId}", thisId.toString()).replace("{grandParentId}", thisParentId.toString()).replace("{grandgrandParentId}", parentId.toString())) }
            							else
            							{navController.navigate("showAllDataPage/{thisParentId}/{grandParentId}".replace("{thisParentId}", thisId.toString()).replace("{grandParentId}", thisParentId.toString())) } }
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
fun CustomBox(imageRes: String?, title: String, description: String, modifier: Modifier = Modifier, color: Int,thisId:Int,thisParentId:Int,parentId:Int,navController: NavController,item:Category,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory) {
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
            if(imageRes != null){
                Image(
                    painter = rememberAsyncImagePainter(model = imageRes),
                    contentDescription = "Image Display",
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
                    modifier = if (imageRes != null) {
                        Modifier.fillMaxWidth().height(100.dp)
                    } else {
                        Modifier.fillMaxWidth().height(170.dp)
                    }
                )

                Spacer(modifier = Modifier.height(5.dp))
                SmallButton(color = R.color.darkBlue, text = "Tambah Kartu",thisId,thisParentId,parentId,navController,0)
                Spacer(modifier = Modifier.height(5.dp))
                SmallButton(color = R.color.teal, text = "Lihat Detail",thisId,thisParentId,parentId,navController,1)

            }


        }

        Box(
            modifier = Modifier.padding(3.dp)
        ){
            Option1(onClickEdit = { navController.navigate("editSet/{thisId}/{thisParentId}/{grandParentId}".replace("{thisId}", thisId.toString()).replace("{thisParentId}", thisParentId.toString()).replace("{grandParentId}", parentId.toString())) },
                onClickDelete = { CategoryViewModel.deleteData(item,thisParentId) })
        }
    }
}

@Composable
fun ResponsiveGridLayout(items: List<Category>,thisParentId:Int,parentId:Int,navController: NavController,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory) {
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
                        imageRes = item.imagePath,
                        title = item.name,
                        description = item.description,
                        modifier = Modifier
                            .width(itemWidth)
                            .height(305.dp),
                        color = colorDict(item.backgroundColor),
                        item.id!!,thisParentId,parentId,navController,item,FlashcardViewModel ,CategoryViewModel
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
