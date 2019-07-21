package com.example.photodisplayer.domain.Repository

import com.example.photodisplayer.domain.model.PhotoSearchDTO
import io.reactivex.Single

interface PhotoRepository {
    fun searchPhoto(keyword: String): Single<PhotoSearchDTO.Photos>
}