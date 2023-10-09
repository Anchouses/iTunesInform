package com.itunesinform.data.repository

import android.content.Context
import com.itunesinform.data.datamodel.AlbumSearchResult
import com.itunesinform.data.datamodel.SongSearchResult
import com.itunesinform.data.retrofitAPI.InterfaceAlbumApi
import com.itunesinform.domain.AlbumModel
import com.itunesinform.domain.RepositoryInterface
import com.itunesinform.domain.SongModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitRepository private constructor(context: Context) : RepositoryInterface {

    private val interceptor = HttpLoggingInterceptor()
    init {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient
        .Builder()
        .readTimeout(60L, TimeUnit.SECONDS)
        .connectTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com").client(client)
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val api: InterfaceAlbumApi = retrofit.create(InterfaceAlbumApi::class.java)
    override suspend fun getAlbumByArtist(text: String): List<AlbumModel> {

        val result: AlbumSearchResult = api.getAlbumsByArtist(text)

        val resultModel: List<AlbumModel> = result.results.map {
                AlbumModel(
                    artistName = it.artistName,
                    artworkUrl100 = it.artworkUrl100,
                    collectionName = it.collectionName,
                    collectionId = it.collectionId,
                    releaseDate = it.releaseDate
                )
            }
        return resultModel
    }

    override suspend fun getSongsByAlbum(text: String): List<SongModel> {
        val result: SongSearchResult = api.getSongsByAlbum(text)

        val resultModel: List<SongModel> = result.results.map {
            SongModel(
                trackName = it.trackName,
                trackNumber = it.trackNumber,
                trackTimeMillis = it.trackTimeMillis,
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

