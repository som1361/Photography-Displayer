package com.example.photodisplayer.DI.component

import com.example.photodisplayer.DI.Scope.ActivityScope
import com.example.photodisplayer.DI.module.ActivityModule
import com.example.photodisplayer.view.MainActivity
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class] , dependencies = [AppComponent ::class])
interface ActivityComponent {
    fun inject(target: MainActivity)

}