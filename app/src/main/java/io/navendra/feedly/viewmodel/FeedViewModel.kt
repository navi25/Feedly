package io.navendra.feedly.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.navendra.feedly.data.Feed
import io.navendra.feedly.data.source.FeedRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class FeedViewModel @Inject constructor(
    private val feedRepository: FeedRepository) : ViewModel(){

    private val feedResult : MutableLiveData<List<Feed>> = MutableLiveData()
    private val feedError : MutableLiveData<String> = MutableLiveData()

    lateinit var disposableObserver: DisposableObserver<Response<List<Feed>>>

    fun feedResult() : MutableLiveData<List<Feed>> = feedResult

    fun feedError() : MutableLiveData<String> = feedError

    fun loadFeeds(){

        disposableObserver = object : DisposableObserver<Response<List<Feed>>>(){
            override fun onComplete() {

            }
            override fun onNext(t: Response<List<Feed>>) {
                feedResult.postValue(t.body())
            }

            override fun onError(e: Throwable) {
                feedError.postValue(e.message)
            }
        }

        feedRepository?.getAllFeed()
            .doOnEach {
                val url = it.value?.raw()?.request()?.url().toString()
                Log.d("GG",url)
            }
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .debounce(400,TimeUnit.MILLISECONDS)
            .subscribe(disposableObserver)

    }

    fun disposeElements(){
        if(null != disposableObserver && !disposableObserver.isDisposed) disposableObserver.dispose()
    }


}