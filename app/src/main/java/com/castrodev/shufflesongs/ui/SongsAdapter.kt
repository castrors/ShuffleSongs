package com.castrodev.shufflesongs.ui

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.castrodev.shufflesongs.data.network.response.Song

class SongsAdapter : ListAdapter<Song, RecyclerView.ViewHolder>(SONG_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SongViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val productItem = getItem(position)
        productItem?.apply {
            (holder as SongViewHolder).bind(this)
        }
    }

    companion object {
        private val SONG_COMPARATOR = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean =
                oldItem.trackName == newItem.trackName

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean =
                oldItem == newItem
        }
    }

}