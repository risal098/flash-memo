package com.example.vanillastarter.pages
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
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
import androidx.navigation.NavController
import com.example.vanillastarter.R
import com.example.vanillastarter.data.Flashcard
import com.example.vanillastarter.func.crudCategory
import com.example.vanillastarter.func.crudFlashcard
import com.wajahatkarim.flippable.FlipAnimationType
import com.wajahatkarim.flippable.Flippable
import com.wajahatkarim.flippable.rememberFlipController
import androidx.compose.ui.platform.LocalContext
import coil3.compose.rememberAsyncImagePainter

@Composable
fun ButtonLink(color: Int, text: String, onClick: ()->Unit){
    Box(
        modifier = Modifier
            .background(
                color = colorResource(color),
                shape = RoundedCornerShape(100.dp)
            ).padding(vertical = 5.dp)
            .clickable {
                onClick()
            }
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
fun BothCard(
//    modifier: Modifier,
//    judul: String,
//    image: Int,
//    desk: String
navController: NavController,
    modifier: Modifier, judul:String, image: String?, desk:String,
    thisParentId:Int, grandParentId:Int,item: Flashcard, FlashcardViewModel: crudFlashcard,
    CategoryViewModel: crudCategory
) {
    val flipController = rememberFlipController()

    Box(
    ){
        Flippable(
            flipController = flipController,
            flipOnTouch = true,
            flipAnimationType = FlipAnimationType.HORIZONTAL_CLOCKWISE,
            frontSide = {
//            FrontCard(judul = judul, image = image, modifier = modifier)
                FrontCard(judul = judul, image = image, modifier = modifier,
                    thisParentId=thisParentId,item=item,FlashcardViewModel= FlashcardViewModel,
                    CategoryViewModel=CategoryViewModel)
            },
            backSide = {
//            BackCard(modifier = modifier, image = image)
                BackCard(desk = desk, modifier = modifier,
                    thisParentId=thisParentId,item=item,FlashcardViewModel= FlashcardViewModel,
                    CategoryViewModel=CategoryViewModel)
            },
            modifier = modifier
        )
        Box(
            modifier = Modifier.padding(3.dp).align(alignment = Alignment.TopEnd)
        ){
            Option1(onClickEdit = { navController.navigate("editFlashcard/{thisId}/{thisParentId}/{grandParentId}".replace("{thisId}", item.id!!.toString()).replace("{thisParentId}", thisParentId.toString()).replace("{grandParentId}", grandParentId.toString())) },
                onClickDelete = { FlashcardViewModel.deleteData(item,thisParentId) })
        }
    }
}

@Composable
fun FrontCard(modifier: Modifier, judul:String,image: String?, thisParentId:Int,item:Flashcard,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .background(
                color = colorResource(R.color.darkBlue),
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        if (image != null) {
            Image(
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = "Image Display",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
            )
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = judul,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(R.color.darkBlue).copy(alpha = 0.8f))
                    .padding(vertical = 15.dp)
            )
        }


    }
}

@Composable
fun BackCard(modifier: Modifier, desk:String = "TEST",
             thisParentId:Int = 0,item:Flashcard,FlashcardViewModel:crudFlashcard,
             CategoryViewModel:crudCategory) {
             val context = LocalContext.current

    Column(

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .background(
                color = colorResource(R.color.darkBlue),
                shape = RoundedCornerShape(20.dp)
            ).padding(10.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.height(190.dp).height(190.dp)
                .verticalScroll(
                    rememberScrollState(),
                    enabled = true,
                    flingBehavior = ScrollableDefaults.flingBehavior()
                )


            ){
            Text(
                text = desk,
                color = Color.White,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(R.color.darkBlue).copy(alpha = 0.8f))
                    .padding(vertical = 15.dp)
                    .padding(bottom = 25.dp)

            )
        }

        if(item.link!=null){
            ButtonLink(color = R.color.teal, onClick = {
                try{

                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                    context.startActivity(intent)
                }catch(e: Exception){
//                    {}
                }}, text = "Link"
            )
        }


    }
}

@Composable
fun TestBackCard(modifier: Modifier, desk:String = "TEST") {

}

@Preview
@Composable
fun liat(){
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = (screenWidth / 2) - 25.dp
    TestBackCard(modifier = Modifier.width(itemWidth)
        .height(250.dp), desk = "loasoas asjasja sjansans ajsnaj sasnasnmas janas asns")
}



@Composable
fun CardLayout(navController: NavController,items:List<Flashcard>,thisParentId:Int,grandParentId:Int,FlashcardViewModel:crudFlashcard ,CategoryViewModel:crudCategory) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val itemWidth = (screenWidth / 2) - 25.dp

    Column(modifier = Modifier.fillMaxSize()) {
        Log.d("CardLayout", "FlashcardList: $items")

        val chunkedItems = items.chunked(2)

        chunkedItems.forEach { rowItems ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 5.dp), // optional padding
                horizontalArrangement = Arrangement.spacedBy(10.dp) // Spasi antar item
            ) {
                rowItems.forEach { item ->

                    BothCard(navController,
                        modifier = Modifier
                            .width(itemWidth)
                            .height(250.dp),
                        image = item.imagePath,
                        desk = item.description,
                        judul = item.name,
                        thisParentId=thisParentId,grandParentId=grandParentId,item=item,FlashcardViewModel=FlashcardViewModel,CategoryViewModel=CategoryViewModel

                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp)) // Spacer after each row
        }
    }
}



