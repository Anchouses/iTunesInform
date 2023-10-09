package com.itunesinform.presentation.albumlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itunesinform.domain.Interactor
import com.itunesinform.domain.AlbumModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AlbumsListViewModel(private val interactor: Interactor): ViewModel(){

    var albumId: Int = 0

    var searchText = ""

    var result = emptyList<AlbumModel>()

    fun getResult(text: String){
        viewModelScope.launch {
            delay(1000L)
            if (text.length >= 2) {
                result = interactor.getAlbumByArtist(text)
            }
        }
    }
}