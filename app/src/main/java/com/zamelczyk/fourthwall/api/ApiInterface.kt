package com.zamelczyk.fourthwall.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("v2/list")
    suspend fun getPics(@Query("page") page: Int): List<Pic>

}