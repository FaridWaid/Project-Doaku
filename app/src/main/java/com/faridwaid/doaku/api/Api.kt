package com.faridwaid.doaku.api

import com.faridwaid.doaku.model.DoaResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("https://doa-doa-api-ahmadramadhan.fly.dev/api")
    fun getPosts(): Call<ArrayList<DoaResponse>>
}