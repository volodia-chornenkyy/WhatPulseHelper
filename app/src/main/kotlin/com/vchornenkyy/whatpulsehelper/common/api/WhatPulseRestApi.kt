package com.vchornenkyy.whatpulsehelper.common.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class WhatPulseRestApi {
    val userApi: UserService

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.whatpulse.org/")
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .build()

        userApi = retrofit.create(UserService::class.java)
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLogger = HttpLoggingInterceptor()
        httpLogger.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
                .addInterceptor(httpLogger)
                .build()
    }
}