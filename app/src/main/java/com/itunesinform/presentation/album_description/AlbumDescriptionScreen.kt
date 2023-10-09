package com.itunesinform.presentation.album_description

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.itunesinform.domain.AlbumModel
import com.itunesinform.domain.SongModel
import com.itunesinform.presentation.navigation.Screen
import com.itunesinform.presentation.theme.DarkPurple
import com.itunesinform.presentation.theme.Grey

@Composable
fun AlbumDescriptionScreen(
    album: AlbumModel,
    viewModel: AlbumDescriptionViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    viewModel.searchAlbum = album.collectionName.replace(" ", "+")
    viewModel.getResult(viewModel.searchAlbum)
    val list = viewModel.result

    Box(modifier.fillMaxSize()){
        Column(
            modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {

            Row(
                modifier
                    .fillMaxWidth()
                    .background(
                        color = Grey,
                        shape = RoundedCornerShape(10.dp)
                    )
            ){
                AsyncImage(
                    model = album.artworkUrl100,
                    contentDescription = "Album artwork",
                    modifier
                        .fillMaxWidth(0.5f)
                        .padding(8.dp)
                        .clip(shape = RoundedCornerShape(10.dp)),
                    alignment = Alignment.TopStart
                )

                Column(horizontalAlignment = Alignment.End){
                    Box(
                        modifier = modifier.align(CenterHorizontally)
                    ){
                        Text(
                            text = album.collectionName,
                            modifier.padding(4.dp),
                            color = Color.White,
                            fontSize = 28.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 36.sp
                        )
                    }

                    Box(
                        modifier = modifier.align(CenterHorizontally)
                    ){
                        Text(
                            text = "Release date: ${viewModel.getDate(album.releaseDate)}",
                            modifier.padding(4.dp),
                            color = Color.White,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 24.sp
                        )
                    }

                    Box(
                        modifier = modifier.align(CenterHorizontally)
                    ){
                        Text(
                            text = album.artistName,
                            modifier.padding(4.dp),
                            color = Color.White,
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            lineHeight = 32.sp
                        )
                    }
                }
            }

            LazyColumn(
                modifier
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp, bottom = 68.dp)
                    .fillMaxWidth()
                    .height(420.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                itemsIndexed(list) {index, item ->
                    Song(song = item, index = index, viewModel = viewModel)
                }
            }
        }

        Button(
            onClick = { navController.navigate(Screen.AlbumsListScreen.route) },
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(60.dp)
                .align(BottomCenter),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                Grey,
                Color.White
            ),
        ) {
            Text(
                text = "Back to list",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun Song(modifier: Modifier = Modifier, song: SongModel, index: Int, viewModel: AlbumDescriptionViewModel){

    val stringTime: String = viewModel.getTime(song.trackTimeMillis)

    Box(
        modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = DarkPurple)
            .padding(4.dp)
    ){

        Box(
            modifier
                .fillMaxWidth(0.8f)
                .align(CenterStart)
                .padding(start = 4.dp)
        ){
            Text(
                text = "${index+1}.  ${song.trackName}",
                fontSize = 16.sp,
                lineHeight = 24.sp,
                color = Color.White
            )
        }

        Spacer(modifier.padding(4.dp))

        Box(
            modifier
                .align(CenterEnd)
                .padding(end = 4.dp)
        ){
            Text(
                text = stringTime,
                modifier.padding(start = 16.dp),
                fontSize = 14.sp,
                color = Color.White
            )
        }
    }
}
