package io.navendra.feedly.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.AndroidInjection
import io.navendra.feedly.R
import io.navendra.feedly.data.Feed
import io.navendra.feedly.viewmodel.FeedViewModel
import io.navendra.feedly.viewmodel.FeedViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(){

    @Inject
    lateinit var feedViewModelFactory: FeedViewModelFactory
    private lateinit var feedViewModel : FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        feedViewModel = ViewModelProviders.of(this, feedViewModelFactory).get(FeedViewModel::class.java)

        feedViewModel.feedResult().observe(this, Observer {
            updateFeedView(it)
        })

        feedViewModel.feedError().observe(this, Observer {
            updateFeedView(it)
        })

        feedViewModel.loadFeeds()
    }

    private fun updateFeedView(feeds : List<Feed>){
        textView.text = feeds.size.toString()
    }

    private fun updateFeedView(error: String){
        textView.text = error
    }

    override fun onStop() {
        feedViewModel.disposeElements()
        super.onStop()

    }
}