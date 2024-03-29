package io.navendra.feedly

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.navendra.feedly.di.components.DaggerAppComponent
import io.navendra.feedly.di.modules.AppModule
import io.navendra.feedly.di.modules.NetworkModule
import javax.inject.Inject

class FeedlyApplication : Application(), HasActivityInjector{

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .networkModule(NetworkModule(AppConstants.BASE_URL))
            .build()
            .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector


}