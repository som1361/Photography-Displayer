package com.example.photodisplayer.DI.component

import com.example.photodisplayer.DI.Scope.AppScope
import com.example.photodisplayer.DI.module.AppModule
import com.example.photodisplayer.application.PhotoApplication
import dagger.Component
@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
fun inject(target:PhotoApplication)
}