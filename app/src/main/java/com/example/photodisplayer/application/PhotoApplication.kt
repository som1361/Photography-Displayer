package com.example.photodisplayer.application

import android.app.Application
import com.example.photodisplayer.DI.component.AppComponent
import com.example.photodisplayer.DI.component.AsyncComponent
import com.example.photodisplayer.DI.component.DaggerAppComponent
import com.example.photodisplayer.DI.component.DaggerAsyncComponent
import com.example.photodisplayer.DI.module.AppModule


class PhotoApplication : Application() {

    lateinit var photoComponent: AppComponent
    companion object {
        private lateinit var  asyncComponent: AsyncComponent
        fun getAsyncComponent(): AsyncComponent{
            return asyncComponent
        }
    }

    private fun initDagger(app: PhotoApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()

    override fun onCreate() {
        super.onCreate()
        photoComponent = initDagger(this)
        asyncComponent = DaggerAsyncComponent.create()
    }
}