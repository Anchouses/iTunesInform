package com.itunesinform.presentation.album_description

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.itunesinform.domain.AlbumModel
import com.itunesinform.presentation.navigation.Screen
import com.itunesinform.presentation.theme.Grey
import com.itunesinform.presentation.theme.GreyBackground

@Composable
fun AlbumDescriptionScreen(
    album: AlbumModel,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    //val itemNumber = navController.previousBackStackEntry?.arguments?.getInt("itemNumber")

    Box(modifier.fillMaxSize()){
        Column(
            modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = album.artworkUrl100,
                contentDescription = "Album artwork",
                modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(shape = RoundedCornerShape(10.dp)),
                alignment = Alignment.TopCenter
            )

            Text(
                text = album.collectionName,
                modifier.padding(16.dp),
                color = Color.Black,
                fontSize = 32.sp,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Artist: ${album.artistName}",
                modifier.padding(16.dp),
                color = Color.Black,
                fontSize = 28.sp,
                textAlign = TextAlign.Center
            )
            LazyColumn(
                modifier
                    .padding(16.dp)
                    .height(200.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.Start,
            ) {

            }
        }
        Button(
            onClick = { navController.navigate(Screen.AlbumsListScreen.route) },
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .height(60.dp)
                .align(BottomCenter),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                Grey,
                Color.Black
            ),
        ) {
            Text(text = "Back to list")
        }
    }
}

@Composable
fun Song(songName: String, modifier: Modifier = Modifier){
    Text(
        text = songName,
        modifier
            .fillMaxSize(),
        fontSize = 20.sp,
        textAlign = TextAlign.Start)
}
