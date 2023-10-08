package com.itunesinform.presentation

import android.app.Application
import com.itunesinform.data.repository.RetrofitRepository
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AlbumApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        RetrofitRepository.initialize(this)

//        startKoin{
//            androidLogger(Level.DEBUG)
//            androidContext(this@EmotionalApplication)
//            modules(listOf(interactorModule, presentationModule))
//        }
    }
}