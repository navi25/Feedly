package io.navendra.feedly.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.navendra.feedly.AppConstants.Room as RoomConstants
import io.navendra.feedly.data.Feed

@Dao
interface FeedDAO{

    @Query(RoomConstants.GET_ALL_FEED)
    fun getAllFeed() : LiveData<List<Feed>>

    @Insert( onConflict = OnConflictStrategy.REPLACE)
    fun insertFeed(feed: Feed)
}