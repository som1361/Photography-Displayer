package com.example.photodisplayer.model

import com.example.photodisplayer.domain.Service.PhotoService
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import io.reactivex.Single


class PhotoSearchModel {
    val organicApiServe by lazy {
        PhotoService.create()
    }

    fun fetchOrganicList(keyword: String): Single<PhotoSearchDTO.Photos> {
        return organicApiServe.searchPhoto(keyword, 30, 1)
    }
}

