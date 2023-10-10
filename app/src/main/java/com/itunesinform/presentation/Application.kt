package com.itunesinform.presentation

import android.app.Application
import com.itunesinform.data.repository.RetrofitRepository

class AlbumApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitRepository.initialize(this)
    }
}