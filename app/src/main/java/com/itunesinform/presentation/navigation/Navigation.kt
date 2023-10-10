package com.itunesinform.presentation.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.itunesinform.data.repository.RetrofitRepository
import com.itunesinform.domain.Interactor
import com.itunesinform.presentation.album_description.AlbumDescriptionScreen
import com.itunesinform.presentation.album_description.AlbumDescriptionViewModel
import com.itunesinform.presentation.albumlist.AlbumsListScreen
import com.itunesinform.presentation.albumlist.AlbumsListViewModel

@Composable
fun Navigation() {

    val retrofitRepository = RetrofitRepository.get()
    val interactor = Interactor(retrofitRepository)
    val listViewModel = AlbumsListViewModel(interactor)
    val detailViewModel = AlbumDescriptionViewModel(interactor)

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AlbumsListScreen.route) {
        composable(route = Screen.AlbumsListScreen.route) {
            AlbumsListScreen(
                modifier = Modifier,
                navController = navController,
                viewModel = listViewModel
            )
        }
        composable(
            route = Screen.AlbumDescriptionScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { entry ->
            val albumId = entry.arguments?.getInt("id")
            val albums = listViewModel.albums.collectAsState()
            val album = albums.value.find { it.collectionId == albumId }
            if (album != null) {
                albumId?.let {
                    AlbumDescriptionScreen(
                        album = album,
                        viewModel = detailViewModel,
                        navController = navController
                    )
                }
            } else {
                Toast.makeText(LocalContext.current, "Something wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }
}


sealed class Screen(val route: String) {
    object AlbumsListScreen : Screen("list_screen")
    object AlbumDescriptionScreen : Screen("description_screen")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}