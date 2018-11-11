package com.castrodev.shufflesongs.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.castrodev.shufflesongs.data.network.response.Song

class SongsAdapter : RecyclerView.Adapter<SongViewHolder>() {

    var dataSet: List<Song> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = dataSet[position]
        holder.bind(song)
    }
}