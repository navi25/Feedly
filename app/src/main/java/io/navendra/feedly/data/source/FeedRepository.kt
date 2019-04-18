package io.navendra.feedly.data.source

import android.util.Log
import io.navendra.feedly.data.Feed
import io.navendra.feedly.data.source.local.FeedDAO
import io.navendra.feedly.data.source.remote.FeedlyApiInterface
import io.reactivex.Observable
import javax.inject.Inject

class FeedRepository @Inject constructor(private val feedlyApi : FeedlyApiInterface,
                                         private val feedDAO: FeedDAO){

    fun getAllFeed() : Observable<List<Feed>>{
        val localFeedsObservable = getFeedsFromLocalDb()
        val remoteFeedsObservable = getFeedsFromRemoteApi()
        return Observable.concatArrayEager(localFeedsObservable, remoteFeedsObservable)
    }

    private fun getFeedsFromLocalDb(): Observable<List<Feed>>{
        return feedDAO.getAllFeed()
            .toObservable()
            .doOnNext {
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
    }

    private fun getFeedsFromRemoteApi(): Observable<List<Feed>>?{
        return feedlyApi.getAllFeed()
            .doOnNext {
                for (item in it) {
                    feedDAO.insertFeed(item)
                }
            }
    }

}