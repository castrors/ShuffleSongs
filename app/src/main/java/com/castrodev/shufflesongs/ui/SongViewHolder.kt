package com.castrodev.shufflesongs.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.castrodev.shufflesongs.R
import com.castrodev.shufflesongs.data.network.response.Song

class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val trackName: TextView = view.findViewById(R.id.track_name)
    private val artistName: TextView = view.findViewById(R.id.artist_name)
//    private val artwork: SimpleDraweeView = view.findViewById(R.id.image)

    private var song: Song? = null


    fun bind(song: Song?) = when (song) {
        null -> {
            val resources = itemView.resources
            trackName.text = resources.getString(R.string.unknown)
            artistName.text = resources.getString(R.string.unknown)

        }
        else -> showSongData(song)
    }


    private fun showSongData(song: Song) {
        this.song = song
        trackName.text = song.trackName
        artistName.text = formatTrackName(song)
    }

    private fun formatTrackName(song: Song) = "${song.artistName} (${song.primaryGenreName})"



    companion object {
        fun create(parent: ViewGroup): SongViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.song_view_item, parent, false)
            return SongViewHolder(view)
        }
    }
}