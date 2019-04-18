package io.navendra.feedly.di.modules

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import io.navendra.feedly.data.source.local.FeedDAO
import io.navendra.feedly.AppConstants.Room as RoomDatabase
import io.navendra.feedly.data.source.local.FeedDatabase
import javax.inject.Singleton

@Module
class AppModule(val app: Application) {

    companion object {
        val MIGRATION_1_2: Migration = object : Migration(1, 2){
            override fun migrate(database: SupportSQLiteDatabase) {
               //ToDo
            }
        }
    }


    @Provides @Singleton
    fun provideApp() : Application = app

    @Provides @Singleton
    fun provideFeedDatabase(app: Application) : FeedDatabase = Room.databaseBuilder(app,
        FeedDatabase::class.java,RoomDatabase.FEED_DATABASE)
//        /*.addMigrations(MIGRATION_1_2)*/
        .fallbackToDestructiveMigration()
        .build()

    @Provides @Singleton
    fun provideFeedDao(feedDatabase: FeedDatabase): FeedDAO = feedDatabase.feedDAO()

}