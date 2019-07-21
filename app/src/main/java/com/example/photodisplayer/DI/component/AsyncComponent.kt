package com.example.photodisplayer.DI.component

import com.example.photodisplayer.DI.Scope.AsyncScope
import com.example.photodisplayer.DI.module.AsyncModule
import dagger.Component

@AsyncScope
@Component(modules = [AsyncModule::class])
interface AsyncComponent {
}