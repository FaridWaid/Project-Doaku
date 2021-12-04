package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faridwaid.doaku.R
import com.faridwaid.doaku.adapter.FavoriteAdapter
import com.faridwaid.doaku.database.Favorite
import com.faridwaid.doaku.model.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {

    private lateinit var model: FavoriteViewModel
    private lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val tIdDoa = intent.getStringExtra(FavoriteActivity.EXTRA_ID)
        val tJudulDoa = intent.getStringExtra(FavoriteActivity.EXTRA_DOA)
        val tAyatDoa = intent.getStringExtra(FavoriteActivity.EXTRA_AYAT)
        val tLatinDoa = intent.getStringExtra(FavoriteActivity.EXTRA_LATIN)
        val tArtiDoa = intent.getStringExtra(FavoriteActivity.EXTRA_ARTINYA)

        //menampilkan list doa
        adapter = FavoriteAdapter()
        model = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        showListDoa()

    }

    private fun showListDoa() {
        var rvNote : RecyclerView = findViewById(R.id.rv_doa)
        rvNote.setHasFixedSize(true)
        rvNote.layoutManager = LinearLayoutManager(this)
        model.getListNotes().observe(this, object : Observer<List<Favorite>> {
            override fun onChanged(t: List<Favorite>?) {
                if (t != null) {
                    adapter.setListNote(t)
                }
                rvNote.adapter = adapter
            }

        })

    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DOA = "extra_doa"
        const val EXTRA_AYAT = "extra_ayat"
        const val EXTRA_LATIN = "extra_latin"
        const val EXTRA_ARTINYA = "extra_artinya"
        const val CREATE_NOTE_REQUEST_CODE = 1
        const val UPDATE_NOTE_REQUEST_CODE = 2
    }
}