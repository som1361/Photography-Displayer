package com.example.photodisplayer.DI.component

import com.example.photodisplayer.DI.Scope.AsyncScope
import com.example.photodisplayer.DI.module.AsyncModule
import com.example.photodisplayer.domain.model.PhotoSearchDTO
import dagger.Component
import io.reactivex.subjects.PublishSubject
import javax.inject.Named

@AsyncScope
@Component(modules = [AsyncModule::class])
interface AsyncComponent {
    @Named(AsyncModule.GET_PHOTOS_OBSERVABLE)
    fun getGetContentObservable(): PublishSubject<PhotoSearchDTO.Photos>

    @Named(AsyncModule.GET_PHOTOS_ERROR_OBSERVABLE)
    fun getGetContentErrorObservable(): PublishSubject<Exception>
}