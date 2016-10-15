package com.vchornenkyy.whatpulsehelper.presenter

import com.vchornenkyy.whatpulsehelper.api.Cache
import com.vchornenkyy.whatpulsehelper.api.InMemoryCache
import com.vchornenkyy.whatpulsehelper.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.api.model.UserResponse
import com.vchornenkyy.whatpulsehelper.view.GeneralInfoView
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GeneralInfoPresenter() {

    var view: GeneralInfoView? = null
    var userSubscription: Subscription? = null

    fun loadUser() {
        val userApi = WhatPulseRestApi().userApi
        val cache: Cache = InMemoryCache.instance
        val username = "temnoi"
        userSubscription = cache.getUser()
                .switchIfEmpty(userApi.getUser(username))
                .map { userResponse -> convert(userResponse) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userResponse ->
                    view?.bindUser(userResponse)

                    // todo take timestamp from 'GeneratedTime'
                    cache.saveUser(userResponse, System.currentTimeMillis())
                }
    }

    //TODO move it somewhere
    fun convert(userResponse: UserResponse): UserResponse {
        // prepare model for UI (for ex. format data/time)
        return userResponse
    }

    fun detach() {
        userSubscription?.unsubscribe()
        userSubscription = null
    }
}
