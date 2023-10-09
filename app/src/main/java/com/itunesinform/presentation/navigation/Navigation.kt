package com.itunesinform.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
fun Navigation(){

    val retrofitRepository = RetrofitRepository.get()
    val interactor = Interactor(retrofitRepository)
    val listViewModel = AlbumsListViewModel(interactor)
    val detailViewModel = AlbumDescriptionViewModel(interactor)

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AlbumsListScreen.route){
        composable(route = Screen.AlbumsListScreen.route) {
           AlbumsListScreen(
                modifier = Modifier,
                navController = navController,
                viewModel = listViewModel
            )
        }
        composable (
            route = Screen.AlbumDescriptionScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { entry ->
            val albumId = entry.arguments?.getInt("id")
            val index = listViewModel.result.indexOfFirst{it.collectionId == albumId}
            if (index != -1) {
                albumId?.let {
                    AlbumDescriptionScreen(
                        album = listViewModel.result[index],
                        detailViewModel,
                        navController
                    )
                }
            } else {
                albumId?.let {
                    AlbumDescriptionScreen(
                        album = listViewModel.result[0],
                        detailViewModel,
                        navController
                    )
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    object AlbumsListScreen: Screen("list_screen")
    object AlbumDescriptionScreen: Screen("description_screen")

    fun withArgs(vararg args: Int): String {
        return buildString {
            append(route)
            args.forEach {arg ->
                append("/$arg")
            }
        }
    }
}