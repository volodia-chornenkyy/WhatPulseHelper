package com.vchornenkyy.whatpulsehelper

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vchornenkyy.whatpulsehelper.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.api.model.UserResponse
import kotlinx.android.synthetic.main.activity_main.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userApi = WhatPulseRestApi().userApi
        val subscription = userApi.getUser("temnoi")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .map { userResponse -> convert(userResponse) }
                .subscribe { userResponse -> jsonFromServer.text = userResponse.toString() }
    }

    //TODO move it somewhere
    fun convert(userResponse: UserResponse): UserResponse {
        // prepare model for UI (for ex. format data/time)
        return userResponse
    }
}
