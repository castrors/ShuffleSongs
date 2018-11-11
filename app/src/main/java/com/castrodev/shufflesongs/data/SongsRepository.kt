package com.castrodev.shufflesongs.data

import android.arch.lifecycle.MutableLiveData
import com.castrodev.shufflesongs.data.network.MusicsApi
import com.castrodev.shufflesongs.data.network.fetchMusics
import com.castrodev.shufflesongs.data.network.response.Song
import com.castrodev.shufflesongs.data.network.response.SongsResult

class SongsRepository(private val api: MusicsApi) : SongsRepositoryContract {

    private var isRequestInProgress = false
    private val dataResult = MutableLiveData<List<Song>>()
    private val networkErrors = MutableLiveData<String>()

    override fun getMusics(): SongsResult {
        requestData()
        return SongsResult(dataResult, networkErrors)
    }

    private fun requestData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        fetchMusics(api,
            { musics ->
                dataResult.postValue(musics)
                isRequestInProgress = false
            },
            { error ->
                networkErrors.postValue(error)
                isRequestInProgress = false
            })
    }


}