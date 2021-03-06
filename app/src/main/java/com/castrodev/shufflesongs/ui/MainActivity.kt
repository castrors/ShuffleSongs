package com.castrodev.shufflesongs.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.castrodev.shufflesongs.R
import com.castrodev.shufflesongs.utilities.Injection

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SongsListViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var loading: ProgressBar
    private var adapter = SongsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        loading = findViewById(R.id.loading)
        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory())
            .get(SongsListViewModel::class.java)
        viewModel.initialize()
        initAdapter()
    }

    private fun initAdapter() {
        showLoading()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        viewModel.viewState.observe(this, Observer {
            hideLoading()
            it?.let { render(it) }
        })
    }

    private fun render(uiModel: SongsUiModel) {
        when (uiModel) {
            is SongsUiModel.ListUpdated -> {
                adapter.dataSet = uiModel.songs
            }
            is SongsUiModel.ErrorOccurred -> {
                Toast.makeText(this, uiModel.error, Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun hideLoading() {
        loading.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun showLoading() {
        loading.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_shuffle -> {
                viewModel.shuffle()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
