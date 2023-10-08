package com.itunesinform.presentation.albumlist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itunesinform.R
import com.itunesinform.presentation.albumlist.AlbumsListViewModel
import com.itunesinform.presentation.theme.Grey
import com.itunesinform.presentation.theme.GreyBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    viewModel: AlbumsListViewModel
) {
    val text = remember { mutableStateOf("") }

    TextField(
        value = text.value,
        onValueChange = { newText ->
            text.value = newText
            viewModel.onSearchTextChange(text.value)
        },
        modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.White, shape = RoundedCornerShape(percent = 10)),
        singleLine = true,
        label = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.search),
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            disabledTextColor = Color.White,
            containerColor = GreyBackground,
            cursorColor = Color.White,
            focusedLabelColor = Grey,
            unfocusedLabelColor = Grey,
            focusedLeadingIconColor = Grey,
            unfocusedLeadingIconColor = Grey,
            disabledLeadingIconColor = Grey,
            placeholderColor = Grey,
            errorCursorColor = Grey,
            //selectionColors = Grey,
            focusedIndicatorColor = Grey,
            unfocusedIndicatorColor = Grey,
            disabledIndicatorColor = Grey,
            errorIndicatorColor = Grey,
            errorLeadingIconColor = Grey,
            focusedTrailingIconColor = Grey,
            unfocusedTrailingIconColor = Grey,
            disabledTrailingIconColor = Grey,
            errorTrailingIconColor = Grey,
            disabledLabelColor = Grey,
            errorLabelColor = Grey,
            disabledPlaceholderColor = Grey,
            focusedSupportingTextColor = Color.White,
            unfocusedSupportingTextColor = Color.White,
            disabledSupportingTextColor = Color.White,
            errorSupportingTextColor = Grey

        )
    )
}