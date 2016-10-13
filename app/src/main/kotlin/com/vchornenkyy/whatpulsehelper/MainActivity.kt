package com.vchornenkyy.whatpulsehelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vchornenkyy.whatpulsehelper.api.Cache
import com.vchornenkyy.whatpulsehelper.api.InMemoryCache
import com.vchornenkyy.whatpulsehelper.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.api.model.UserResponse
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        jsonFromServer.setOnClickListener { loadUser() }
    }

    private fun loadUser() {
        val userApi = WhatPulseRestApi().userApi
        val cache: Cache = InMemoryCache.instance
        val username = "temnoi"
        cache.getUser()
                .switchIfEmpty(userApi.getUser(username))
                .map { userResponse -> convert(userResponse) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userResponse ->
                    jsonFromServer.text = userResponse.toString()

                    // todo take timestamp from 'GeneratedTime'
                    cache.saveUser(userResponse, System.currentTimeMillis())
                }
    }

    //TODO move it somewhere
    fun convert(userResponse: UserResponse): UserResponse {
        // prepare model for UI (for ex. format data/time)
        return userResponse
    }
}
