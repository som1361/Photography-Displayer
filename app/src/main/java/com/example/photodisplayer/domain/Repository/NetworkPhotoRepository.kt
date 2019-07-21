package com.example.photodisplayer.model

import com.example.photodisplayer.domain.Repository.PhotoRepository
import com.example.photodisplayer.domain.Service.PhotoService
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import io.reactivex.Single


class NetworkPhotoRepository: PhotoRepository {
    val organicApiServe by lazy {
        PhotoService.create()
    }

    override fun searchPhoto(keyword: String): Single<PhotoSearchDTO.Photos> {
        return organicApiServe.searchPhoto(keyword, 30, 1)
    }
}

