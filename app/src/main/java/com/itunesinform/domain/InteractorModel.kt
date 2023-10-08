package com.itunesinform.domain

class InteractorModel(private val repositoryInterface: RepositoryInterface) {

    suspend fun getAlbumByArtist(text: String): List<AlbumModel> {
        return repositoryInterface.getAlbumByArtist(text)
    }

}