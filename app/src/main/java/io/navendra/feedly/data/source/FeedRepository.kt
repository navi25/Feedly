package io.navendra.feedly.data.source

import android.util.Log
import io.navendra.feedly.data.Feed
import io.navendra.feedly.data.source.local.FeedDAO
import io.navendra.feedly.data.source.remote.FeedlyApiInterface
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class FeedRepository @Inject constructor(private val feedlyApi : FeedlyApiInterface,
                                         private val feedDAO: FeedDAO){

    fun getAllFeed() : Observable<Response<List<Feed>>>{
//        val localFeedsObservable = getFeedsFromLocalDb()
        val remoteFeedsObservable = getFeedsFromRemoteApi()
        return remoteFeedsObservable
//        return Observable.concatArrayEager(localFeedsObservable, remoteFeedsObservable)
    }

    private fun getFeedsFromLocalDb(): Observable<List<Feed>>{
        return feedDAO.getAllFeed()
            .toObservable()
            .doOnNext {
                Log.e("REPOSITORY DB *** ", it.size.toString())
            }
    }

    private fun getFeedsFromRemoteApi(): Observable<Response<List<Feed>>>{
        return feedlyApi.getAllFeed()
            .doOnNext {
                val feeds = it.body() ?: emptyList()
                for (item in feeds) {
                    feedDAO.insertFeed(item)
                }
            }
    }

}