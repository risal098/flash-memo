package com.example.vanillastarter.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanillastarter.shared.NavigationScreen
import com.example.vanillastarter.utils.parseColor
import com.example.vanillastarter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationBar(
    title: String,
    currentRoute: String?,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                println("Debug: currentRoute = '$currentRoute'")

                when (currentRoute) {

                    // MainScreen with parentId null (Title with Profile Picture)
                    "${NavigationScreen.MainScreen.name}/null" -> {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                        ) {
                            Text(
                                text = title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        }

                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your avatar resource
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )
                    }

                    // AddScreen and EditScreen do not need NavBar (Nothing)
                    in arrayOf(NavigationScreen.AddScreen.name, NavigationScreen.EditScreen.name) -> {

                    }

                    // MainScreen with parentId not null (Title and Icon back)
                    else -> {
                        Text(
                            text = title,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = "Back"
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(parseColor("#F6F4F0")),
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
fun NavigationBarPreview() {
    MaterialTheme {
        NavigationBar(
            title = "MainScreenWithoutParentId",
            currentRoute = "${NavigationScreen.MainScreen.name}/null",
            canNavigateBack = true,
            navigateUp = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationBarWithBackButtonPreview() {
    MaterialTheme {
        NavigationBar(
            title = "MainScreenWithParentId",
            currentRoute = "${NavigationScreen.MainScreen.name}/1",
            canNavigateBack = true,
            navigateUp = {}
        )
    }
}
