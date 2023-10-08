package com.itunesinform.presentation.albumlist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.itunesinform.domain.AlbumModel
import com.itunesinform.presentation.navigation.Screen
import com.itunesinform.presentation.theme.Grey
import com.itunesinform.presentation.theme.GreyBackground
import com.itunesinform.presentation.theme.ITunesInformTheme


@Composable
fun AlbumsListScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AlbumsListViewModel
) {
    ITunesInformTheme {
        Column(modifier.fillMaxSize()) {
            SearchField(viewModel = viewModel)
            val searchText by viewModel.searchText.collectAsState()
            viewModel.getResult(searchText)
            AlbumsList(
                list = viewModel.result,
                navController,
                viewModel
            )
        }
    }
}

@Composable
fun AlbumsList(
    list: List<AlbumModel>,
    navController: NavController,
    viewModel: AlbumsListViewModel
) {
    LazyColumn() {
        itemsIndexed(list) { index, item ->
            viewModel.id = index
            Item(album = item, navController, viewModel.id)
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
                    color = Color.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = album.artistName,
                    color = Color.Black,
                    fontSize = 20.sp
                )
            }
        }
    }
}



