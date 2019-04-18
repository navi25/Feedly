package io.navendra.feedly.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.navendra.feedly.AppConstants.Room as RoomConstants
import io.navendra.feedly.data.Feed
import io.reactivex.Single

@Dao
interface FeedDAO{

    @Query(RoomConstants.GET_ALL_FEED)
    fun getAllFeed() : Single<List<Feed>>

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(feed: Feed)
}