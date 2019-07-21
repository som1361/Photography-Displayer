package com.example.photodisplayer.DI.module

import com.example.photodisplayer.DI.Scope.AsyncScope
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import com.example.photodisplayer.domain.model.PhotoSearchDTO.Photo

import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Named

@Module
class AsyncModule() {
    companion object {
        const val GET_PHOTOS_ERROR_OBSERVABLE = "getPhotosErrorObservable"
        const val GET_PHOTOS_OBSERVABLE = "getPhotosObservable"
    }

    @Provides
    @AsyncScope
    @Named(GET_PHOTOS_ERROR_OBSERVABLE)
    fun provideGetPhotosErrorObservable(): PublishSubject<Exception> = PublishSubject.create()

    @Provides
    @AsyncScope
    @Named(GET_PHOTOS_OBSERVABLE)
    fun provideGetPhotosObservable(): PublishSubject<PhotoSearchDTO.Photos> = PublishSubject.create()

}