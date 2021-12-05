package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.faridwaid.doaku.R

class CreateDoaFavorite : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_doa_favorite)

        val resultIntent = Intent(this@CreateDoaFavorite, FavoriteActivity::class.java)
        val tIdDoa = intent.getStringExtra(EXTRA_ID)
        val tJudulDoa = intent.getStringExtra(EXTRA_DOA)
        val tAyatDoa = intent.getStringExtra(EXTRA_AYAT)
        val tLatinDoa = intent.getStringExtra(EXTRA_LATIN)
        val tArtiDoa = intent.getStringExtra(EXTRA_ARTINYA)
        if (!TextUtils.isEmpty(tIdDoa)){
            resultIntent.putExtra(NEW_DOA, tJudulDoa)
            resultIntent.putExtra(NEW_AYAT, tAyatDoa)
            resultIntent.putExtra(NEW_LATIN, tLatinDoa)
            resultIntent.putExtra(NEW_ARTI, tArtiDoa)
            setResult(RESULT_OK, resultIntent)
            startActivity(resultIntent)
        } else {
            setResult(RESULT_CANCELED)
        }
        finish()
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
    }
}