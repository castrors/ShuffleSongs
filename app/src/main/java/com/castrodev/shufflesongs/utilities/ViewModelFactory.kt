package com.castrodev.shufflesongs.utilities

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.castrodev.shufflesongs.data.SongsRepositoryContract
import com.castrodev.shufflesongs.ui.SongsListViewModel


class ViewModelFactory(private val repository: SongsRepositoryContract) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SongsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SongsListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}