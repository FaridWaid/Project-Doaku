package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
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


        //menampilkan list doa
        adapter = FavoriteAdapter()
        model = ViewModelProvider(this).get(FavoriteViewModel::class.java)
        showListDoa()

    }

    private fun showListDoa() {
        var rvNote : RecyclerView = findViewById(R.id.rv_doa)
        rvNote.setHasFixedSize(true)
        rvNote.layoutManager = LinearLayoutManager(this)
        model.getListFavorites().observe(this, object : Observer<List<Favorite>> {
            override fun onChanged(t: List<Favorite>?) {
                if (t != null) {
                    adapter.setListNote(t)
                }
                rvNote.adapter = adapter
            }

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        resultCode == RESULT_OK
        if (requestCode == CREATE_NOTE_REQUEST_CODE && resultCode == RESULT_OK) {
            val titleDoa = data?.getStringExtra(CreateDoaFavorite.NEW_DOA)
            val ayatDoa = data?.getStringExtra(CreateDoaFavorite.NEW_AYAT)
            val latineDoa = data?.getStringExtra(CreateDoaFavorite.NEW_LATIN)
            val artiDoa = data?.getStringExtra(CreateDoaFavorite.NEW_ARTI)
            if (titleDoa != null && ayatDoa != null && latineDoa != null && artiDoa != null) {
                model.insert(titleDoa, ayatDoa, latineDoa, artiDoa)
            }
            Toast.makeText(this, "Doa Berhasil Ditambahkan!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Doa Tidak Berhasil Ditambahkan!", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val NEW_DOA = "new_doa"
        const val NEW_AYAT = "new_ayat"
        const val NEW_LATIN = "new_latin"
        const val NEW_ARTI = "new_arti"
        const val CREATE_NOTE_REQUEST_CODE = 1
        const val UPDATE_NOTE_REQUEST_CODE = 2
    }
}