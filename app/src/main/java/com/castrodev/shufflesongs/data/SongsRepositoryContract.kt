package com.castrodev.shufflesongs.data

import android.arch.lifecycle.MutableLiveData
import com.castrodev.shufflesongs.ui.SongsUiModel

interface SongsRepositoryContract {
    fun getMusics(viewState : MutableLiveData<SongsUiModel>)
    fun shuffle(viewState: MutableLiveData<SongsUiModel>)
}