package io.navendra.feedly.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import io.navendra.feedly.view.MainActivity

@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity() : MainActivity
}