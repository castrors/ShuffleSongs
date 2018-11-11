package com.castrodev.shufflesongs.data

import android.arch.lifecycle.MutableLiveData
import com.castrodev.shufflesongs.data.network.MusicsApi
import com.castrodev.shufflesongs.data.network.fetchMusics
import com.castrodev.shufflesongs.data.network.response.Song
import com.castrodev.shufflesongs.data.network.response.SongsResult

class SongsRepository(private val api: MusicsApi) : SongsRepositoryContract {

    private var songsList = mutableListOf<Song>()
    private var isRequestInProgress = false
    private val dataResult = MutableLiveData<List<Song>>()
    private val networkErrors = MutableLiveData<String>()

    override fun getMusics(): SongsResult {
        requestData()
        return SongsResult(dataResult, networkErrors)
    }

    override fun shuffle() {
        songsList.shuffle()
        if (!isShuffleCorrect()) shuffle()

        dataResult.postValue(songsList)
    }

    private fun isShuffleCorrect() =
        (0 until songsList.size - 1).none { songsList[it].artistName == songsList[it + 1].artistName }


    private fun requestData() {
        if (isRequestInProgress) return

        isRequestInProgress = true
        fetchMusics(api,
            { musics ->
                songsList.addAll(musics.filter { it.wrapperType == "track" })
                dataResult.postValue(songsList)
                isRequestInProgress = false
            },
            { error ->
                networkErrors.postValue(error)
                isRequestInProgress = false
            })
    }
}