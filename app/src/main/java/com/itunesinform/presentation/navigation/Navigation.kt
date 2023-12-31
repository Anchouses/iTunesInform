package com.itunesinform.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.itunesinform.data.repository.RetrofitRepository
import com.itunesinform.domain.InteractorModel
import com.itunesinform.presentation.album_description.AlbumDescriptionScreen
import com.itunesinform.presentation.albumlist.AlbumsListScreen
import com.itunesinform.presentation.albumlist.AlbumsListViewModel

@Composable
fun Navigation(){

    val retrofitRepository = RetrofitRepository.get()
    val interactor = InteractorModel(retrofitRepository)
    val viewModel = AlbumsListViewModel(interactor)
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.AlbumsListScreen.route){
        composable(route = Screen.AlbumsListScreen.route) {
           AlbumsListScreen(
                modifier = Modifier,
                navController = navController,
                viewModel = viewModel
            )
        }
        composable (
            route = Screen.AlbumDescriptionScreen.route + "/{index}",
            arguments = listOf(
                navArgument("index") {
                    type = NavType.IntType
                    nullable = false
                }
            )
        ) { entry ->
            val index = entry.arguments?.getInt("index")
            Log.d("Args", index.toString())
            index?.let { AlbumDescriptionScreen(album = viewModel.result[index], navController) }
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