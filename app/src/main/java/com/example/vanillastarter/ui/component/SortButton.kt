package com.example.vanillastarter.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vanillastarter.shared.SortType
import com.example.vanillastarter.shared.toText
import com.example.vanillastarter.utils.parseColor

@Composable
fun SortButton(
    onSortTypeSelected: (SortType) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
        ) {
        Button(
            onClick = { expanded = true },
            colors = ButtonDefaults.buttonColors(containerColor = parseColor("#4DA1A9")),
            shape = RoundedCornerShape(50),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 5.dp),
//            modifier = Modifier.height(25.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(start = 24.dp, end = 24.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Sort",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    Icons.AutoMirrored.Filled.List,
                    contentDescription = "Sort Icon",
                    modifier = Modifier
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF7F7F7), shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            SortType.entries.forEachIndexed { index, sortType ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = sortType.toText(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    },
                    onClick = {
                        onSortTypeSelected(sortType)
                        expanded = false
                    },
                    modifier = Modifier
                        .background(if (index % 2 == 0) Color(0xFFF1F1F1) else Color.Transparent)
                )
                if (index < SortType.entries.size - 1) {
                    HorizontalDivider(thickness = 4.dp, color = Color.Gray)
                }
            }
        }
    }
}


@Preview(showBackground = false)
@Composable
fun PreviewSortButton() {
    var selectedSortType = SortType.NAME_ASC

    SortButton(
        onSortTypeSelected = { selectedSortType = it }
    )
}