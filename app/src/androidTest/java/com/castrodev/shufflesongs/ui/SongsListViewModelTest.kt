package com.castrodev.shufflesongs.ui

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.castrodev.shufflesongs.data.SongsRepositoryContract
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SongsListViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val observer: Observer<SongsUiModel> = mock()

    private val mockSongsRepository: SongsRepositoryContract = mock()
    private val viewModel = SongsListViewModel(mockSongsRepository)


    @Before
    fun setUpSongsViewModel() {
        viewModel.viewState.observeForever(observer)
    }

    @Test
    fun getMusicsList_whenFetchesNewData() {
        val list = generateSongsList(1)
        val songsUiModel = SongsUiModel.ListUpdated(list)
        viewModel.viewState.postValue(songsUiModel)
        verify(observer).onChanged(songsUiModel)
    }

    @Test
    fun shufflesMusicList_whenClickShuffleButton(){
        stubSongsRepositoryGetSongs(3)
        viewModel.shuffle()
        verify(mockSongsRepository).shuffle(viewModel.viewState)
    }

    @Test
    fun getError_whenRequestReturnError(){
        val songsUiModel = SongsUiModel.ErrorOccurred("Error")
        viewModel.viewState.postValue(songsUiModel)
        verify(observer).onChanged(songsUiModel)
    }

    private fun stubSongsRepositoryGetSongs(amount: Int) {
        val list = generateSongsList(amount)
        val songsUiModel = SongsUiModel.ListUpdated(list)
        viewModel.viewState.postValue(songsUiModel)
    }

}