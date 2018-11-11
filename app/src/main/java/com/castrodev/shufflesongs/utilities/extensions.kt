package com.castrodev.shufflesongs.utilities

import com.castrodev.shufflesongs.data.network.response.Song

fun List<Song>.shouldBeShuffled() =
    (0 until this.size - 1).any  { this[it].artistName == this[it + 1].artistName }