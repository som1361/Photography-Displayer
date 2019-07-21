package com.example.photodisplayer.DI.component

import com.example.photodisplayer.DI.Scope.ActivityScope
import com.example.photodisplayer.DI.module.ActivityModule
import dagger.Component

@ActivityScope
@Component(modules = [ActivityModule::class])
interface ActivityComponent {
}