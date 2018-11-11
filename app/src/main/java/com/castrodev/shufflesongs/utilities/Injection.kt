package com.castrodev.shufflesongs.utilities

import android.arch.lifecycle.ViewModelProvider
import com.castrodev.shufflesongs.data.SongsRepository
import com.castrodev.shufflesongs.data.SongsRepositoryContract
import com.castrodev.shufflesongs.data.network.MusicsApi

object Injection {

    private fun provideSongsRepository(): SongsRepositoryContract {
        return SongsRepository(MusicsApi.create())
    }

    fun provideViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(provideSongsRepository())
    }
}