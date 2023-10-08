package com.itunesinform.presentation.albumlist

import androidx.lifecycle.ViewModel
import com.itunesinform.domain.InteractorModel
import com.itunesinform.domain.AlbumModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AlbumsListViewModel(interactorModel: InteractorModel): ViewModel(){

    private val interactor = interactorModel

    var id: Int = 0

    private val _searchText = MutableStateFlow("".replace(" ", "+", ignoreCase = true))
    var searchText = _searchText.asStateFlow()

    var result = emptyList<AlbumModel>()

    fun onSearchTextChange(text: String){
        _searchText.value = text
    }

    fun getResult(text: String){
        CoroutineScope(Dispatchers.IO).launch {
            result = interactor.getAlbumByArtist(text)
        }
    }
}