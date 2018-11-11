package com.castrodev.shufflesongs.data

import com.castrodev.shufflesongs.data.network.response.Song
import com.castrodev.shufflesongs.utilities.shouldBeShuffled
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SongsShuffleRuleTest{

    @Test
    fun verifyShuffleRule_withArtistNamesNext(){
        val songs = generateSongsList(3)
        val assertionSongs = mutableListOf<Song>()
        assertionSongs.add(songs[0].copy(artistName = "joao"))
        assertionSongs.add(songs[1].copy(artistName = "joao"))
        assertionSongs.add(songs[2].copy(artistName = "maria"))

        assertTrue(assertionSongs.shouldBeShuffled())
    }

    @Test
    fun verifyShuffleRule_withArtistNamesNotNext(){
        val songs = generateSongsList(3)
        val assertionSongs = mutableListOf<Song>()
        assertionSongs.add(songs[0].copy(artistName = "joao"))
        assertionSongs.add(songs[1].copy(artistName = "maria"))
        assertionSongs.add(songs[2].copy(artistName = "joao"))

        assertFalse(assertionSongs.shouldBeShuffled())
    }
}