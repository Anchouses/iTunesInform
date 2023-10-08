package com.itunesinform.data.retrofitAPI

import com.itunesinform.data.datamodel.SearchResult
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceAlbumApi {
    @GET("search?entity=album&limit=25")
    suspend fun getAlbumsByArtist(@Query("term") text: String): SearchResult
}

// https://itunes.apple.com/search?term=mickle+jackson&entity=song&limit=1