package com.itunesinform.data.repository

import android.content.Context
import com.itunesinform.data.datamodel.SearchResult
import com.itunesinform.data.retrofitAPI.InterfaceAlbumApi
import com.itunesinform.domain.RepositoryInterface
import com.itunesinform.domain.AlbumModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitRepository private constructor(context: Context) : RepositoryInterface {

    override suspend fun getAlbumByArtist(text: String): List<AlbumModel> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient
            .Builder()
            .readTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://itunes.apple.com").client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()

        val api = retrofit.create(InterfaceAlbumApi::class.java)

        val result: SearchResult = api.getAlbumsByArtist(text)

        val resultModel: List<AlbumModel> = result.results.map {
                AlbumModel(
                    artistName = it.artistName,
                    artworkUrl100 = it.artworkUrl100,
                    collectionName = it.collectionName,
//                    releaseDate = it.releaseDate,
//                    shortDescription = it.shortDescription,
//                    trackCount = it.trackCount
                )
            }

        return resultModel
    }

    companion object {
        private var INSTANCE: RetrofitRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = RetrofitRepository(context)
            }
        }

        fun get(): RetrofitRepository {
            return INSTANCE ?: throw IllegalStateException("where repository?")
        }
    }
}

