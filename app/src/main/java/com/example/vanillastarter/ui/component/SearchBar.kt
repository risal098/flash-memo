package com.example.vanillastarter.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vanillastarter.utils.parseColor

@Composable
fun SearchBar(
    searchQuery: String,
    onInput: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { onInput },
        placeholder = { Text("Search") },
        shape = RoundedCornerShape(50),
        colors = OutlinedTextFieldDefaults.colors(parseColor("#E4E4E4")),
        trailingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search") },
        modifier = Modifier.fillMaxWidth()
    )
}


@Preview(showBackground = false)
@Composable
fun PreviewSearchBar() {
    var searchQuery = ""

    SearchBar(
        searchQuery = searchQuery,
        onInput = { searchQuery = it }
    )
}