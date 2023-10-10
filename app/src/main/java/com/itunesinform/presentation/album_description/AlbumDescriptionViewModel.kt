package com.itunesinform.presentation.album_description

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itunesinform.domain.Interactor
import com.itunesinform.domain.SongModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class AlbumDescriptionViewModel(private val interactor: Interactor): ViewModel() {

    private val _songs = MutableStateFlow<List<SongModel>>(emptyList())
    val songs: StateFlow<List<SongModel>> = _songs

    private var requestJob: Job? = null
    fun getResult(text: String) {
        val transText = text.replace(" ", "+")
        requestJob?.cancel()
        requestJob = viewModelScope.launch {
            _songs.value = interactor.getSongsByAlbum(transText)
        }
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(date: String): String? {
        val inputDate = date.take(10)

        val inputFormat = SimpleDateFormat("yyyy-MM-dd")
        val outputFormat = SimpleDateFormat("dd.MM.yyyy")

        val transDate = inputFormat.parse(inputDate)

        return transDate?.let { outputFormat.format(it) }
    }

    fun getTime(time: Int): String {
        return if (time == 0) "" else {
            val seconds: Int = (time / 1000) % 60
            val minutes: Int = (time / 60000) % 60
            val sec: String = if ("$seconds".length == 1) "0${seconds}" else "$seconds"
            "${minutes}:${sec}"
        }
    }
}

