package com.example.photodisplayer.DI.module

import android.app.Activity
import android.content.Context
import com.example.photodisplayer.DI.Scope.ActivityScope
import com.example.photodisplayer.domain.Repository.PhotoRepository
import com.example.photodisplayer.model.NetworkPhotoRepository
import com.example.photodisplayer.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityScope
    fun providesContext(): Context = activity

    /////ViewModel////////////////////////////////////
    @Provides
    @ActivityScope
    fun providesViewModel(photoRepository: PhotoRepository) = MainViewModel(photoRepository)

    /////Model////////////////////////////////////////
    @Provides
    @ActivityScope
    fun providesPhotoRepository(): PhotoRepository = NetworkPhotoRepository()
}
