package com.faridwaid.doaku.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    //list dari doa - doa
    private var list = ArrayList<DoaResponse>()
    //untuk title action bar
    private var title: String = "Daftar Doa"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doa)

        //action bar
        val actionBar = supportActionBar
        actionBar!!.title = title

        //back button
        actionBar.setDisplayHomeAsUpEnabled(true)

        //menampilkan list doa
        showListDoa()
    }

    //fungsi untuk menampilkan semua doa
    private fun showListDoa() {
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

            }
        })
    }

    //back button
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}