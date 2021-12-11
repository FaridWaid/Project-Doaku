package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.faridwaid.doaku.R
import java.text.SimpleDateFormat
import java.util.*

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        //Menyembunyikan support action bar
        supportActionBar?.hide()

        //memanggil fungsi untuk menampilkan hari dan tanggal
        setDate()

        //pindah ke halaman doa
        doaActivity()

        //pindah ke halaman pray time
        prayTimeActivity()

        //pindah ke halaman note
        noteActivity()

        //pindah ke halaman about developer
        aboutDeveloperActivity()
    }

    //fungsi untuk pindah ke about developer activity
    private fun aboutDeveloperActivity() {
        var toAboutDeveloper: ConstraintLayout = findViewById(R.id.about_developer)

        toAboutDeveloper.setOnClickListener {
            Intent(this@FirstActivity, AboutDeveloperActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    //fungsi untuk pindah ke pray time/jadwal sholat activity
    private fun prayTimeActivity() {
        var toPrayTime: CardView = findViewById(R.id.jadwal_sholat)

        toPrayTime.setOnClickListener {
            Intent(this@FirstActivity, PrayTimeActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    //fungsi untuk pindah ke note activity activity
    private fun noteActivity() {
        var toCatatan: CardView = findViewById(R.id.catatan)

        toCatatan.setOnClickListener {
            Intent(this@FirstActivity, NoteActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    //fungsi untuk pindah ke doa - doa activity
    private fun doaActivity() {
        var toDoa: CardView = findViewById(R.id.doadoa)

        toDoa.setOnClickListener {
            Intent(this@FirstActivity, DoaActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    //fungsi hari dan tanggal
    private fun setDate() {
        val time: TextView = findViewById(R.id.time)
        val date: TextView = findViewById(R.id.date)

        //mengambil nilai calender dan menentukan format calender
        var calendar: Calendar = Calendar.getInstance()
        var simpleDateFormat = SimpleDateFormat("EEEE,")
        var simpleDateFormat2 = SimpleDateFormat("dd LLLL yyyy")

        var dateTime: String = simpleDateFormat.format(calendar.time).toString()
        var dateTime2: String = simpleDateFormat2.format(calendar.time).toString()

        time.text = dateTime
        date.text = dateTime2
    }
}