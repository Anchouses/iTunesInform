package com.itunesinform.data.retrofitAPI

import com.itunesinform.data.datamodel.AlbumSearchResult
import com.itunesinform.data.datamodel.SongSearchResult
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceAlbumApi {
    @GET("search?entity=album&limit=25")
    suspend fun getAlbumsByArtist(@Query("term") text: String): AlbumSearchResult

    @GET("search?entity=song")
    suspend fun getSongsByAlbum(@Query("term") text: String): SongSearchResult

}