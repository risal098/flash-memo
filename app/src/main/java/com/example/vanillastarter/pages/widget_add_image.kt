package com.example.vanillastarter.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
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