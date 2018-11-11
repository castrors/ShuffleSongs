package com.castrodev.shufflesongs.data.network.response

data class Result(
    val resultCount: Int,
    val results: List<Song>
)