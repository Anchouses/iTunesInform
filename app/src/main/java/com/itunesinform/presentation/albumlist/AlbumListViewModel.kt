package com.itunesinform.presentation.albumlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itunesinform.domain.AlbumModel
import com.itunesinform.domain.Interactor
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumsListViewModel(private val interactor: Interactor): ViewModel(){

    private val _albums = MutableStateFlow<List<AlbumModel>>(emptyList())
    val albums: StateFlow<List<AlbumModel>> = _albums

    private var requestJob: Job? = null

    fun getResult(text: String) {
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            delay(1000L)
            if (text.length >= 3) {
                _albums.value = interactor.getAlbumByArtist(text)
            }
        }
    }
}