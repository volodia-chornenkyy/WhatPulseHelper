package com.vchornenkyy.whatpulsehelper.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class WhatPulseRestApi {
    val userApi: UserService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.whatpulse.org/")
                .client(OkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        userApi = retrofit.create(UserService::class.java)
    }
}