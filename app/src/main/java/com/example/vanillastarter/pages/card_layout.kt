package com.example.vanillastarter.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanillastarter.R
import androidx.navigation.NavController
import com.example.vanillastarter.func.*
import com.example.vanillastarter.data.*
@Composable
fun BothCard(modifier: Modifier,onClick:()->Unit, judul:String,image: Int,desk:String, issFront:Boolean){
    var isFront = issFront
    Box (
        modifier.clickable {
            onClick
            if(isFront){
                isFront = false
            } else{
                isFront = true
            }
        }
    ){
        if(isFront){
            FrontCard(judul = judul, image = image, modifier = modifier)
        } else{
//            BackCard(image = image)
        }
    }
}

@Composable
fun BackCard(modifier: Modifier, image: Int){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(
                color = colorResource(R.color.darkBlue),
                shape = RoundedCornerShape(20.dp),

                )

    ) {
        Text(
            text = "CODING",
            color = Color.White,
            fontSize = 12.sp,

            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
                .background(
                    color = colorResource(R.color.darkBlue).copy(alpha = 0.8f)
                )
                .padding(vertical = 15.dp),


            )
    }
}

@Composable
fun FrontCard(modifier: Modifier,image: Int, judul: String){
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = colorResource(R.color.darkBlue),
                shape = RoundedCornerShape(20.dp),

                )
    ){
        if(image!=0){
            Image(
                painter = painterResource(id = image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
//                alpha = 0F,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {
            Text(
                text = "CODING",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .background(
                        color = colorResource(R.color.darkBlue).copy(alpha = 0.8f)
                    )
                    .padding(vertical = 15.dp),


                )
        }

        Box(
            modifier = Modifier.padding(3.dp).align(alignment = Alignment.TopEnd)
        ){
            Option1(onClickEdit = {  },
                onClickDelete = {  })
        }
    }
}


@Composable
fun CardLayout(items:List<Flashcard>) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = (screenWidth / 2) - 25.dp

    Column(modifier = Modifier.fillMaxSize()) {
        val chunkedItems = items.chunked(2)

        chunkedItems.forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp), // optional padding
                horizontalArrangement = Arrangement.spacedBy(10.dp) // Spasi antar item
            ) {
                rowItems.forEach { item ->

                    BothCard(
                        onClick = {
                        },
                        modifier = Modifier
                            .width(itemWidth)
                            .height(250.dp),
                        image = R.drawable.androidparty,
                        desk = item.description,
                        judul = item.name,
                        issFront = true
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp)) // Spacer after each row
        }
    }
}


