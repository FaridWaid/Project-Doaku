package com.faridwaid.doaku.activity

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.faridwaid.doaku.R
import com.faridwaid.doaku.api.RetrofitClient
import com.faridwaid.doaku.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PrayTimeActivity : AppCompatActivity() {

    //set titel
    private var title: String = "Informasi Jadwal Sholat"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pray_time)

        //mendefiniskan action bar
        val actionBar = supportActionBar
        actionBar!!.title = title

        //back button
        actionBar.setDisplayHomeAsUpEnabled(true)

        // menentuka nilai kota
        var etCity: TextView = findViewById(R.id.kota)
        etCity.text = "surabaya"
        val formatCity = "surabaya"

        //set tanggal sekarang
        setDate()
        val dateTime = setDate()
        showListDoa(dateTime, formatCity)

        //fungsi untuk ganti tanggal
        changeDate()

        //ganti tanggal ketika button sudah diklik
        var btnChangeCity: Button = findViewById(R.id.btnGantiKota)
        btnChangeCity.setOnClickListener {
            val formatCity = changeCity()
            val date: TextView = findViewById(R.id.tanggal)
            val dateTime = date.text.toString()
            showListDoa(dateTime, formatCity)
        }
    }

    //fungsi ganti tanggal
    private fun changeDate() {
        var btnChangeDate: TextView = findViewById(R.id.btnGantiTanggal)
        val myCalender = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR, year)
            myCalender.set(Calendar.MONTH, month)
            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateCalender(myCalender)
            val updateNewDate = updateCalender(myCalender)
            val date: TextView = findViewById(R.id.kota)
            val formatCity = date.text.toString()
            showListDoa(updateNewDate, formatCity)
        }

        //ketika button di klik calender dialog akan muncul
        btnChangeDate.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    //fungsi ganti kota
    private fun changeCity(): String {
        var etCity: TextView = findViewById(R.id.kota)
        val updateCity = etCity.text.toString()
        return updateCity
    }

    //fungsi hari dan tanggal
    private fun setDate(): String {
        val date: TextView = findViewById(R.id.tanggal)

        //mendifinisikan nilai calender dan menentukan format calender
        var calendar: Calendar = Calendar.getInstance()
        var simpleDateFormat2 = SimpleDateFormat("yyyy-MM-dd")
        var dateTime: String = simpleDateFormat2.format(calendar.time).toString()
        date.text = dateTime
        return dateTime
    }

    //fungsi update calender
    private fun updateCalender(myCalender: Calendar): String {
        var tvDate: TextView = findViewById(R.id.tanggal)
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.US)
        val updateDate = sdf.format(myCalender.time)
        tvDate.setText(updateDate)
        return updateDate
    }

    //fungsi untuk menampilkan semua data
    private fun showListDoa(formatDate: String, city: String) {

        //parameter yang diset dan digunakan untuk paremeter API
        val parameters = HashMap<String, String>()
        parameters["date"] = formatDate
        parameters["city"] = city


        RetrofitClient.instance.getPrayTime(parameters).enqueue(object : Callback<PrayTimeResponseOke> {
            override fun onResponse(
                call: Call<PrayTimeResponseOke>,
                response: Response<PrayTimeResponseOke>
            ) {
                var tvSubuh : TextView = findViewById(R.id.subuh)
                var tvDuhur : TextView = findViewById(R.id.duhur)
                var tvAsar : TextView = findViewById(R.id.asar)
                var tvMaghrib : TextView = findViewById(R.id.magrib)
                var tvIsya : TextView = findViewById(R.id.isyak)

                val oke = response.body()!!.results.datetime[0]

                val text: String = "Subuh: ${oke.times.Imsak}"
                tvSubuh.text = text
                val text2: String = "Duhur: ${oke.times.Dhuhr}"
                tvDuhur.text = text2
                val text3: String = "Ashar: ${oke.times.Asr}"
                tvAsar.text = text3
                val text4: String = "Maghrib: ${oke.times.Maghrib}"
                tvMaghrib.text = text4
                val text5: String = "Isya': ${oke.times.Isha}"
                tvIsya.text = text5

            }

            override fun onFailure(call: Call<PrayTimeResponseOke>, t: Throwable) {

            }
        })
    }

    //fungsi untuk back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}