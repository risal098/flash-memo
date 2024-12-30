package com.example.vanillastarter.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.vanillastarter.R

@Composable
fun FilterBox(modifier: Modifier){
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorResource(R.color.teal),
                shape = RoundedCornerShape(100.dp)
            )
            .padding(vertical = 5.dp, horizontal = 20.dp)
    ){
        Text("Filter", color = colorResource(R.color.white))
        Image(
            painter = painterResource(id = R.drawable.green_filter),
            contentDescription = null,
            contentScale = ContentScale.Crop,
//                alpha = 0F,
            modifier = Modifier
                .size(20.dp)
        )

    }
}