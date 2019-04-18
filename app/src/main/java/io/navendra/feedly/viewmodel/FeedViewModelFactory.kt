package io.navendra.feedly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject

class FeedViewModelFactory @Inject constructor(
    val viewModel: FeedViewModel) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FeedViewModel::class.java)){
            return viewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}