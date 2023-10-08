package com.itunesinform.domain

interface RepositoryInterface {

    suspend fun getAlbumByArtist(text: String): List<AlbumModel>
}