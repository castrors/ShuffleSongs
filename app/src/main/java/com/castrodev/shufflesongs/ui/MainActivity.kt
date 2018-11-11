package com.castrodev.shufflesongs.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.castrodev.shufflesongs.R
import com.castrodev.shufflesongs.data.network.response.Song
import com.castrodev.shufflesongs.utilities.Injection

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SongsListViewModel
    private lateinit var recyclerView: RecyclerView
    private var adapter = SongsAdapter()
    private var songsList = mutableListOf<Song>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory())
            .get(SongsListViewModel::class.java)

        initAdapter()
    }

    private fun initAdapter() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel.songs.data.observe(this, Observer {
            it?.let { songs ->
                songsList.addAll(songs.filter { song -> song.wrapperType == "track" })
                adapter.submitList(songsList)
            }

        })
    }
}
