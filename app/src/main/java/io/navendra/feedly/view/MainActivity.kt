package io.navendra.feedly.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import io.navendra.feedly.R
import io.navendra.feedly.data.Feed
import io.navendra.feedly.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private var feedViewModel: FeedViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        feedViewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)

        feedViewModel?.feedResult()?.observe(this, Observer {
            updateFeedView(it)
        })

        feedViewModel?.loadFeeds()
    }

    fun updateFeedView(feeds : List<Feed>){
        textView.text = feeds.size.toString()
    }

    override fun onStop() {
        feedViewModel?.disposeElements()
        super.onStop()

    }
}