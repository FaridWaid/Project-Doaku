package com.faridwaid.doaku.api

import com.faridwaid.doaku.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap
import kotlin.collections.ArrayList

interface Api {

    @GET("https://doa-doa-api-ahmadramadhan.fly.dev/api")
    fun getPosts(): Call<ArrayList<DoaResponse>>

    @GET("day.json")
    fun getPrayTime(
//        @Query("city") city: String,
//        @Path("date") date: String
        @QueryMap parameters: HashMap<String, String>
    ): Call<PrayTimeResponseOke>
}