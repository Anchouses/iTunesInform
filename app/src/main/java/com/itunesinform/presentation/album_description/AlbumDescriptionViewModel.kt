package com.itunesinform.presentation.album_description

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itunesinform.domain.Interactor
import com.itunesinform.domain.SongModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

class AlbumDescriptionViewModel(private val interactor: Interactor): ViewModel() {

    var searchAlbum = ""

    var result = emptyList<SongModel>()

    fun getResult(text: String){
        viewModelScope.launch {
            result = interactor.getSongsByAlbum(text)
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
        val seconds: Int = time / 1000
        val minutes: Int = seconds / 60
        val ost: Int = (seconds - (seconds % 60)) / 10
        val ostStr: String = if ("$ost".length != 1) "$ost" else "0${ost}"
        return "${minutes}:$ostStr"
    }
}

