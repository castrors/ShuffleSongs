package com.castrodev.shufflesongs.data

import com.castrodev.shufflesongs.data.network.response.Song

fun generateSongsList(amount: Int, wrapperType: String = "music") = (1..amount).map {index ->
    Song(
        index,
        "Artist Name $index",
        "Artist Type",
        "artworkUrl",
        index,
        "Collection Name",
        "Country",
        index,
        "Primary Genre Name",
        "01/01/2018",
        "Censored Name",
        "Track Explicitness",
        "Track Name",
        index,
        wrapperType
    )
}