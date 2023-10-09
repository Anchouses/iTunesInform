package com.itunesinform.domain

class Interactor(private val repositoryInterface: RepositoryInterface) {

    suspend fun getAlbumByArtist(text: String): List<AlbumModel> {
        return repositoryInterface.getAlbumByArtist(text)
    }

    suspend fun getSongsByAlbum(text: String): List<SongModel> {
        return repositoryInterface.getSongsByAlbum(text)
    }

}