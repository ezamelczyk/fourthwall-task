package com.zamelczyk.fourthwall

import com.zamelczyk.fourthwall.api.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class Locator {

    val apiInterface: ApiInterface by lazy {
        Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}