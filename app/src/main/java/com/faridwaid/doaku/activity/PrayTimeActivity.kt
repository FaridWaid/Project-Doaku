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

    var calendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pray_time)

        var etCity: TextView = findViewById(R.id.kota)
        etCity.text = "surabaya"
        val formatCity = "surabaya"
        setDate()
        val dateTime = setDate()
        showListDoa(dateTime, formatCity)
        changeDate()

        var btnChangeCity: Button = findViewById(R.id.btnGantiKota)
        btnChangeCity.setOnClickListener {
            val formatCity = mantapJiwa()
            val date: TextView = findViewById(R.id.tanggal)
            val dateTime = date.text.toString()
            showListDoa(dateTime, formatCity)
        }
    }

    private fun changeDate() {
        var btnChangeDate: TextView = findViewById(R.id.btnGantiTanggal)
        val myCalender = Calendar.getInstance()
        val datePicker = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            myCalender.set(Calendar.YEAR, year)
            myCalender.set(Calendar.MONTH, month)
            myCalender.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateCalender(myCalender)
            val mantap = updateCalender(myCalender)
            val date: TextView = findViewById(R.id.kota)
            val formatCity = date.text.toString()
            showListDoa(mantap, formatCity)
        }

        btnChangeDate.setOnClickListener {
            DatePickerDialog(this, datePicker, myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                myCalender.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun mantapJiwa(): String {
        var etCity: TextView = findViewById(R.id.kota)
        val alhamdulillah = etCity.text.toString()
        return alhamdulillah
    }

    //fungsi hari dan tanggal
    private fun setDate(): String {
        val date: TextView = findViewById(R.id.tanggal)

        // get the Long type value of the current system date
        var calendar: Calendar = Calendar.getInstance()
        var simpleDateFormat2 = SimpleDateFormat("yyyy-MM-dd")
        var dateTime: String = simpleDateFormat2.format(calendar.time).toString()
        date.text = dateTime
        return dateTime
    }

    private fun updateCalender(myCalender: Calendar): String {
        var tvDate: TextView = findViewById(R.id.tanggal)
        val format = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(format, Locale.US)
        val updateDate = sdf.format(myCalender.time)
        tvDate.setText(updateDate)
        return updateDate
    }

    private fun showListDoa(formatDate: String, city: String) {
        var tvText: TextView = findViewById(R.id.tvResponseCode)
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
                tvText.text = t.message
            }
        })
    }
}