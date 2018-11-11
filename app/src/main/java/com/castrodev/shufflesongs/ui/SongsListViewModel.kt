package com.castrodev.shufflesongs.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.castrodev.shufflesongs.data.SongsRepositoryContract


class SongsListViewModel(private val repository: SongsRepositoryContract) : ViewModel() {

    val viewState = MutableLiveData<SongsUiModel>()

    fun initialize(){
        repository.getMusics(viewState)
    }

    fun shuffle() = repository.shuffle(viewState)
}