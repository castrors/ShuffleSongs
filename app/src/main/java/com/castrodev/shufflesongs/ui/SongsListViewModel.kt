package com.castrodev.shufflesongs.ui

import android.arch.lifecycle.ViewModel
import com.castrodev.shufflesongs.data.SongsRepositoryContract
import com.castrodev.shufflesongs.data.network.response.SongsResult


class SongsListViewModel(repository: SongsRepositoryContract) : ViewModel() {
    val songs: SongsResult = repository.getMusics()
}