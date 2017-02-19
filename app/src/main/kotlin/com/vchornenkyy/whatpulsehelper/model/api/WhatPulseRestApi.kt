package com.vchornenkyy.whatpulsehelper.model.api

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

class WhatPulseRestApi {

    private val retrofit: Retrofit

    private var userApi: UserService? = null
    private var teamApi: TeamService? = null

    init {
        retrofit = Retrofit.Builder()
                .baseUrl("http://api.whatpulse.org/")
                .client(getOkHttpClient())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(ObjectMapper().registerModule(KotlinModule())))
                .build()
    }

    fun getUserApi(): UserService {
        if (userApi == null) {
            userApi = retrofit.create(UserService::class.java)
        }
        return userApi!!
    }

    fun getTeamApi(): TeamService {
        if (teamApi == null) {
            teamApi = retrofit.create(TeamService::class.java)
        }
        return teamApi!!
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLogger = HttpLoggingInterceptor()
        httpLogger.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient().newBuilder()
                .addInterceptor(httpLogger)
                .build()
    }
}