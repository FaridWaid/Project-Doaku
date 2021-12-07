package com.faridwaid.doaku.api

import com.faridwaid.doaku.model.DoaResponse
import com.faridwaid.doaku.model.Oke
import com.faridwaid.doaku.model.PrayTimeResponse
import retrofit2.Call
import retrofit2.http.GET
import kotlin.collections.ArrayList

interface Api {

    @GET("https://doa-doa-api-ahmadramadhan.fly.dev/api")
    fun getPosts(): Call<ArrayList<DoaResponse>>

    @GET("day.json?city=mecca&date=2021-12-07")
    fun getPrayTime(
//        @Path("idKota") kotaId: String,
//        @Path("idTanggal") tanggalId: String
    ): Call<PrayTimeResponse>
}