package com.faridwaid.doaku.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faridwaid.doaku.R
import com.faridwaid.doaku.adapter.DoaAdapter
import com.faridwaid.doaku.api.RetrofitClient
import com.faridwaid.doaku.model.DoaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DoaActivity : AppCompatActivity() {

    private var list = ArrayList<DoaResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doa)

        //menampilkan list doa
        showListDoa()

        //menambah favorite doa
        favoriteDoa()
    }

    private fun favoriteDoa() {

    }

    private fun showListDoa() {
        var tvText: TextView = findViewById(R.id.tvResponseCode)
        var rvPost: RecyclerView = findViewById(R.id.rv_doa)
        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getPosts().enqueue(object : Callback<ArrayList<DoaResponse>>{
            override fun onResponse(
                call: Call<ArrayList<DoaResponse>>,
                response: Response<ArrayList<DoaResponse>>
            ) {
                val listResponse = response.body()
                listResponse?.let { list.addAll(it) }
                val adapter = DoaAdapter(list)
                rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<DoaResponse>>, t: Throwable) {
                tvText.text = t.message
            }

        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}