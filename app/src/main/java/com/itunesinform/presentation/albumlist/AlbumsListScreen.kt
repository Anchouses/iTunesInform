package com.itunesinform.presentation.albumlist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.itunesinform.R
import com.itunesinform.domain.AlbumModel
import com.itunesinform.presentation.navigation.Screen
import com.itunesinform.presentation.theme.Grey
import com.itunesinform.presentation.theme.GreyBackground
import com.itunesinform.presentation.theme.ITunesInformTheme
import com.itunesinform.presentation.theme.Purple80

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumsListScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumsListViewModel,
    navController: NavController
) {

    val albumsState = viewModel.albums.collectAsState()

    ITunesInformTheme {
        Column(modifier.fillMaxSize()) {
            val text = remember { mutableStateOf("") }
            TextField(
                value = text.value,
                onValueChange = { newText ->
                    text.value = newText
                    viewModel.getResult(text.value)
                },
                modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(percent = 20)),
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
                    containerColor = Grey,
                    cursorColor = Color.White,
                    focusedLabelColor = Purple80,
                    unfocusedLabelColor = Purple80,
                    focusedLeadingIconColor = Color.White,
                    unfocusedLeadingIconColor = Color.White,
                    disabledLeadingIconColor = Color.White,
                    placeholderColor = Grey,
                    errorCursorColor = Grey,
                    focusedIndicatorColor = Grey,
                    unfocusedIndicatorColor = GreyBackground,
                    disabledIndicatorColor = Grey,
                    errorIndicatorColor = Grey,
                )
            )

            AlbumsList(
                list = albumsState,
                navController
            )
        }
    }
}

@Composable
fun AlbumsList(
    list: State<List<AlbumModel>>,
    navController: NavController,
) {

    LazyColumn {
        itemsIndexed(list.value) { _, item ->
            Item(album = item,
                navController,
                item.collectionId)
        }
    }
}

@Composable
fun Item(
    album: AlbumModel,
    navController: NavController,
    index: Int
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { navController.navigate(Screen.AlbumDescriptionScreen.withArgs(index)) },
        shape = RoundedCornerShape(percent = 10),
        colors = CardDefaults.cardColors(Grey),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
        ) {
            AsyncImage(
                model = album.artworkUrl100,
                contentDescription = "Album artwork",
                Modifier
                    .height(130.dp)
                    .padding(end = 16.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
            )
            Column(Modifier.weight(1f)) {
                Text(
                    text = album.collectionName,
                    textAlign = TextAlign.Start,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = album.artistName,
                    color = Color.White,
                    fontSize = 20.sp
                )
            }
        }
    }
}



