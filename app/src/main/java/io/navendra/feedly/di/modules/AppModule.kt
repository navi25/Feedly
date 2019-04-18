package io.navendra.feedly.di.modules

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.navendra.feedly.data.source.local.FeedDAO
import io.navendra.feedly.AppConstants.Room as RoomDatabase
import io.navendra.feedly.data.source.local.FeedDatabase
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    @Provides @Singleton
    fun provideApp() : Application = app

    @Provides @Singleton
    fun provideFeedDatabase(app: Application) : FeedDatabase = Room.databaseBuilder(app,
        FeedDatabase::class.java,RoomDatabase.FEED_DATABASE).build()

    @Provides @Singleton
    fun provideFeedDao(feedDatabase: FeedDatabase): FeedDAO = feedDatabase.feedDAO()

}