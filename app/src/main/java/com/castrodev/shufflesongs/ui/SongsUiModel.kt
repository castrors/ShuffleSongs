package com.castrodev.shufflesongs.ui

import com.castrodev.shufflesongs.data.network.response.Song

sealed class SongsUiModel {

    data class ListUpdated(val songs: List<Song>) : SongsUiModel()
    data class ErrorOccurred(val error: String) : SongsUiModel()
}
