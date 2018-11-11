package com.castrodev.shufflesongs.data

import com.castrodev.shufflesongs.data.network.response.SongsResult

interface SongsRepositoryContract {
    fun getMusics(): SongsResult
}