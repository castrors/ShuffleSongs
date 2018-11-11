package com.castrodev.shufflesongs.data.network.response

import android.arch.lifecycle.MutableLiveData

data class SongsResult(
    val data: MutableLiveData<List<Song>>,
    val networkErrors: MutableLiveData<String>
)