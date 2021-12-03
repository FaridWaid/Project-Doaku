package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.faridwaid.doaku.R
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //memanggil fungsi untuk menampilkan hari dan tanggal
        setDate()

        //pindah ke halaman doa
        doaActivity()
    }

    private fun doaActivity() {
        var toDoa: CardView = findViewById(R.id.doadoa)

        toDoa.setOnClickListener {
            Intent(this@MainActivity, DoaActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    //fungsi hari dan tanggal
    private fun setDate() {
        val time: TextView = findViewById(R.id.time)
        val date: TextView = findViewById(R.id.date)

        // get the Long type value of the current system date
        var calendar: Calendar = Calendar.getInstance()
        var simpleDateFormat = SimpleDateFormat("EEEE,")
        var simpleDateFormat2 = SimpleDateFormat("dd LLLL yyyy")

        var dateTime: String = simpleDateFormat.format(calendar.time).toString()
        var dateTime2: String = simpleDateFormat2.format(calendar.time).toString()

        time.text = dateTime
        date.text = dateTime2
    }
}