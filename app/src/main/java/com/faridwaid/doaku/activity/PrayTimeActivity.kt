package com.faridwaid.doaku.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faridwaid.doaku.R
import com.faridwaid.doaku.adapter.DoaAdapter
import com.faridwaid.doaku.adapter.PrayTimeAdapter
import com.faridwaid.doaku.adapter.PrayTimeResponseFix
import com.faridwaid.doaku.api.RetrofitClient
import com.faridwaid.doaku.model.DoaResponse
import com.faridwaid.doaku.model.PrayTimeResponse
import com.faridwaid.doaku.model.Times
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrayTimeActivity : AppCompatActivity() {

    private var list = ArrayList<Times>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pray_time)

        showListDoa()
    }

    private fun showListDoa() {
        var tvText: TextView = findViewById(R.id.tvResponseCode)
        var rvPost: RecyclerView = findViewById(R.id.rv_doa)
        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getPrayTime().enqueue(object : Callback<PrayTimeResponse> {
            override fun onResponse(
                call: Call<PrayTimeResponse>,
                response: Response<PrayTimeResponse>
            ) {
                val listResponse = response.body()!!
                list.addAll(listResponse)
                val adapter = PrayTimeAdapter(list)
                rvPost.adapter = adapter

            }

            override fun onFailure(call: Call<PrayTimeResponse>, t: Throwable) {
                tvText.text = t.message
            }
//            override fun onResponse(
//                call: Call<PrayTimeResponse>,
//                response: Response<PrayTimeResponse>
//            ) {
//
//                val listResponse = response.body()
//                listResponse?.let { list.addAll(it) }
//                val adapter = PrayTimeAdapter(list)
//                rvPost.adapter = adapter
////                var tvSubuh : TextView = findViewById(R.id.subuh)
////                var tvDuhur : TextView = findViewById(R.id.duhur)
////                var tvAsar : TextView = findViewById(R.id.asar)
////                var tvMaghrib : TextView = findViewById(R.id.magrib)
////                var tvIsya : TextView = findViewById(R.id.isyak)
////
////                val oke = response.body()!!
////
////                val text2: String = "userId: ${oke.Dhuhr}"
////                tvDuhur.text = text2
////                val text3: String = "userId: ${oke.Asr}"
////                tvAsar.text = text3
////                val text4: String = "userId: ${oke.Maghrib}"
////                tvMaghrib.text = text4
////                val text5: String = "userId: ${oke.Isha}"
////                tvIsya.text = text5
//
//            }
//
//            override fun onFailure(call: Call<PrayTimeResponse>, t: Throwable) {
//                tvText.text = t.message
//            }
//
//        })
        })
    }
}