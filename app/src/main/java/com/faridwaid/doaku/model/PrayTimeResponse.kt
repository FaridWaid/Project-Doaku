package com.faridwaid.doaku.model

import com.faridwaid.doaku.adapter.PrayTimeResponseFix
import retrofit2.Call

data class PrayTimeResponse(
    val results: Call<DateTime>
)

