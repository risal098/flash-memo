package com.example.vanillastarter.pages
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanillastarter.R
import androidx.navigation.NavController
import com.example.vanillastarter.func.*
import com.example.vanillastarter.data.*
@Composable
fun LayoutAddCard(navController: NavController,thisParentId:Int,parentId:Int,grandParentId:Int,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory,onPickImage: () -> Unit,imageUri: Uri?,subCategory:Category?=null){
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
                text = "TAMBAH KARTU",
                lineHeight = 50.sp,
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

                        TextFieldCard(navController,thisParentId,parentId,grandParentId,FlashcardViewModel ,CategoryViewModel,onPickImage,imageUri)
                    }
                }
            }
        }
    }
}


