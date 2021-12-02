package com.faridwaid.doaku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //memanggil fungsi untuk menampilkan hari dan tanggal
        setDate()
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