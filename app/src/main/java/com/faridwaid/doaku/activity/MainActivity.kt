package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.faridwaid.doaku.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Menyembunyikan action bar
        supportActionBar?.hide()

        //Pindah activity setelah beberapa detik
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, FirstActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}