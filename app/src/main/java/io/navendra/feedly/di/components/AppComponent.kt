package io.navendra.feedly.di.components

import android.app.Application
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.navendra.feedly.di.modules.AppModule
import io.navendra.feedly.di.modules.BuilderModule
import io.navendra.feedly.di.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, BuilderModule::class, AppModule::class,
    NetworkModule::class])
interface AppComponent {
    fun inject(app : Application)
}