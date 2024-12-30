package com.example.vanillastarter.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanillastarter.R



@Composable
fun AddImageBox(onClick: ()->Unit, isPictureAdded: Boolean){
    Box(
        modifier = Modifier.background(
            color = colorResource(R.color.lightGrey),
            shape = RoundedCornerShape(20.dp)
        ).fillMaxWidth().height(179.dp).
        clickable (){ onClick }
    ){
        Image(
            painter = painterResource(id = R.drawable.add_image),
            contentDescription = null,
            contentScale = ContentScale.Fit,

            modifier = Modifier
                .size(78.dp)
                .align(Alignment.Center)

        )
        if (isPictureAdded){
            Image(
                painter = painterResource(id = R.drawable.androidparty),
                contentDescription = null,
                contentScale = ContentScale.Crop,

                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .clip(RoundedCornerShape(20.dp))
            )
        }
    }
}

@Composable
fun LayoutAddSet(){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Scaffold {
        padding ->

        Box(
            modifier = Modifier
                .padding(0.dp)
                .fillMaxWidth()
                .height(screenHeight)
                .background(color = colorResource(R.color.teal))

        ){
            Image(
                painter = painterResource(id = R.drawable.add_set),
                contentDescription = null,
                contentScale = ContentScale.Fit,
//                alpha = 1F,
                modifier = Modifier
                    .size(220.dp)
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
            )



            Text(
                text = "TAMBAH SET",
                fontSize = 48.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.width(screenWidth*2/3)
                    .padding(vertical = 40.dp, horizontal = 20.dp ),
                color = colorResource(R.color.mint),

            )

            LazyColumn (
            ){
                item {
                    Spacer(Modifier.height(200.dp))
                }
                item{
                    Column (
                        modifier = Modifier.width(screenWidth).defaultMinSize(minHeight = screenHeight-200.dp)
                            .background(
                                color = colorResource(R.color.white),
                                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)

                            ).padding(top = 20.dp, start = 20.dp, end = 20.dp)

                    ){
                        AddImageBox(onClick = { }, false)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewLayoutAddSet() {
    LayoutAddSet()
//    AddCardOrSet()
}