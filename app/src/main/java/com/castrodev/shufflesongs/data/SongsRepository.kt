package com.castrodev.shufflesongs.data

import android.arch.lifecycle.MutableLiveData
import com.castrodev.shufflesongs.data.network.MusicsApi
import com.castrodev.shufflesongs.data.network.fetchMusics
import com.castrodev.shufflesongs.data.network.response.Song
import com.castrodev.shufflesongs.ui.SongsUiModel
import com.castrodev.shufflesongs.utilities.shouldBeShuffled

class SongsRepository(private val api: MusicsApi) : SongsRepositoryContract {

    private var songsList = mutableListOf<Song>()
    private var isRequestInProgress = false

    override fun getMusics(viewState : MutableLiveData<SongsUiModel>) {
        if (isRequestInProgress) return

        isRequestInProgress = true
        fetchMusics(api,
            { musics ->
                songsList.addAll(musics.filter { it.wrapperType == "track" })
                viewState.postValue(SongsUiModel.ListUpdated(songsList))
                isRequestInProgress = false
            },
            { error ->
                viewState.postValue(SongsUiModel.ErrorOccurred(error))
                isRequestInProgress = false
            })
    }

    override fun shuffle(viewState: MutableLiveData<SongsUiModel>) {
        songsList.shuffle()
        if (songsList.shouldBeShuffled()) shuffle(viewState)

        viewState.postValue(SongsUiModel.ListUpdated(songsList))
    }
}