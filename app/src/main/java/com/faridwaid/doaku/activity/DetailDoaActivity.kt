package com.faridwaid.doaku.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.faridwaid.doaku.R

class DetailDoaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_doa)

        //Menamilkan content doa
        setContentDoa()
    }

    private fun setContentDoa() {
        val tvSetTitle: TextView = findViewById(R.id.title_doa)
        val tvSetAyat: TextView = findViewById(R.id.ayat_doa)
        val tvSetLatin: TextView = findViewById(R.id.latin_doa)
        val tvSetArti: TextView = findViewById(R.id.arti_doa)

        val tIdDoa = intent.getStringExtra(EXTRA_ID)
        val tJudulDoa = intent.getStringExtra(EXTRA_DOA)
        val tAyatDoa = intent.getStringExtra(EXTRA_AYAT)
        val tLatinDoa = intent.getStringExtra(EXTRA_LATIN)
        val tArtiDoa = intent.getStringExtra(EXTRA_ARTINYA)

        tvSetTitle.text = tJudulDoa
        tvSetAyat.text = tAyatDoa
        tvSetLatin.text = tLatinDoa
        tvSetArti.text = tArtiDoa
    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_DOA = "extra_doa"
        const val EXTRA_AYAT = "extra_ayat"
        const val EXTRA_LATIN = "extra_latin"
        const val EXTRA_ARTINYA = "extra_artinya"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}