package com.castrodev.shufflesongs.data.network.response

data class Song(
    val artistId: Int,
    val artistName: String,
    val artistType: String,
    val artworkUrl: String,
    val collectionId: Int,
    val collectionName: String,
    val country: String,
    val id: Int,
    val primaryGenreName: String,
    val releaseDate: String,
    val trackCensoredName: String,
    val trackExplicitness: String,
    val trackName: String,
    val trackTimeMillis: Int,
    val wrapperType: String
)