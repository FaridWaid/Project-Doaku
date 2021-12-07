package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.TextView
import com.faridwaid.doaku.R

class CreateDoaFavorite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_doa_favorite)

        val tIdDoa = intent.getStringExtra(EXTRA_ID)
        val tJudulDoa = intent.getStringExtra(EXTRA_DOA)
        val tAyatDoa = intent.getStringExtra(EXTRA_AYAT)
        val tLatinDoa = intent.getStringExtra(EXTRA_LATIN)
        val tArtiDoa = intent.getStringExtra(EXTRA_ARTINYA)

        val title_note : TextView = findViewById(R.id.ayat_doa_doa)
        val et_note : TextView = findViewById(R.id.latin_doa_doa)
        val okela : TextView = findViewById(R.id.title_doa_favorite)
        val mantap : TextView = findViewById(R.id.arti_doa_doa)

        title_note.text = tAyatDoa
        et_note.text = tLatinDoa
        okela.text = tJudulDoa
        mantap.text = tArtiDoa

        val btn_save_note : Button = findViewById(R.id.btn_save_note)
        btn_save_note.setOnClickListener {
            Intent().also {
                if (!TextUtils.isEmpty(et_note.text)){
                    val note = et_note.text.toString()
                    val title = title_note.text.toString()
                    val heem = okela.text.toString()
                    val yowes = mantap.text.toString()
                    it.putExtra(NEW_DOA, heem)
                    it.putExtra(NEW_AYAT, title)
                    it.putExtra(NEW_LATIN, note)
                    it.putExtra(NEW_ARTI, yowes)
                    setResult(RESULT_OK, it)
                    startActivityForResult(it, CREATE_NOTE_REQUEST_CODE)
                    finish()
                } else {
                    setResult(RESULT_CANCELED)
                }
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DOA = "extra_doa"
        const val EXTRA_AYAT = "extra_ayat"
        const val EXTRA_LATIN = "extra_latin"
        const val EXTRA_ARTINYA = "extra_artinya"
        const val NEW_DOA = "new_doa"
        const val NEW_AYAT = "new_ayat"
        const val NEW_LATIN = "new_latin"
        const val NEW_ARTI = "new_arti"
        const val CREATE_NOTE_REQUEST_CODE = 1
    }
}