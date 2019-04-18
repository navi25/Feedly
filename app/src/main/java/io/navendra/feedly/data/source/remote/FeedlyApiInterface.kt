package io.navendra.feedly.data.source.remote

import io.navendra.feedly.AppConstants
import io.navendra.feedly.data.Feed
import io.reactivex.Observable
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface FeedlyApiInterface{

    @GET(AppConstants.FEEDS_ENDPOINT)
    fun getAllFeed() : Observable<Response<List<Feed>>>

    @GET(AppConstants.FEED_ITEM_ENDPOINT)
    fun getFeedById(@Path(AppConstants.FEED_ID) feedId: Long) : Deferred<Response<List<Feed>>>

}