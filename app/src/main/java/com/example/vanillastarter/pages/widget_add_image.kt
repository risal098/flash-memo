package com.example.vanillastarter.pages

import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil3.compose.ImagePainter
import com.example.vanillastarter.R
import com.example.vanillastarter.utils.saveImageToInternalStorage

@Composable
fun AddImageBox(onClick: ()->Unit, isPictureAdded: Boolean, imagePath: MutableState<String?>){
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                val localPath = saveImageToInternalStorage(context, uri)
                if (localPath != null) {
                    Log.d("ImagePath", "Image saved to: $localPath")
                    imagePath.value = localPath // Update the state with the image path
                } else {
                    Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
                }
            }
        }
    )

    Box(
        modifier = Modifier.background(
            color = colorResource(R.color.lightGrey),
            shape = RoundedCornerShape(20.dp)
        ).fillMaxWidth().height(179.dp).
        clickable (){ launcher.launch("image/*") }
    ){
        Image(
            painter = painterResource(id = R.drawable.add_image),
            contentDescription = null,
            contentScale = ContentScale.Fit,

            modifier = Modifier
                .size(78.dp)
                .align(Alignment.Center)

        )
        if (isPictureAdded && imagePath.value != null) {
            // Display the selected image
            val painter =
                rememberAsyncImagePainter(model = imagePath.value) // Use Coil for image loading
            Image(
                painter = painter,
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