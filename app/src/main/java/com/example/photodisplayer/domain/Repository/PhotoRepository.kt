package com.example.photodisplayer.domain.Repository

import com.example.photodisplayer.domain.model.PhotoSearchDTO
import io.reactivex.Single

interface PhotoRepository {
    fun searchPhoto(keyword: String, perPage: Int, page: Int): Single<PhotoSearchDTO.Photos>
}