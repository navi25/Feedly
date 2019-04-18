package io.navendra.feedly.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.navendra.feedly.data.Feed

@Database(entities = [Feed::class],version = 1)
abstract class LocalDB : RoomDatabase(){
    abstract fun feedDAO(): FeedDAO
}