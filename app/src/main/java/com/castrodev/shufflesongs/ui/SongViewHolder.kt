package com.castrodev.shufflesongs.ui

import android.graphics.drawable.BitmapDrawable
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.castrodev.shufflesongs.R
import com.castrodev.shufflesongs.data.network.response.Song
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


class SongViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val trackName: TextView = view.findViewById(R.id.track_name)
    private val artistName: TextView = view.findViewById(R.id.artist_name)
    private val artwork: ImageView = view.findViewById(R.id.artwork)

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
        Picasso.get().load(song.artworkUrl).into(artwork, object : Callback {
            override fun onSuccess() {
                val imageBitmap = (artwork.drawable as BitmapDrawable).bitmap
                val imageDrawable = RoundedBitmapDrawableFactory.create(artwork.resources, imageBitmap)
                imageDrawable.isCircular = true
                imageDrawable.cornerRadius = Math.max(imageBitmap.width, imageBitmap.height) / 2.0f
                artwork.setImageDrawable(imageDrawable)
            }

            override fun onError(exception: Exception) {
                artwork.setImageResource(R.drawable.ic_launcher_foreground)
            }
        })
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