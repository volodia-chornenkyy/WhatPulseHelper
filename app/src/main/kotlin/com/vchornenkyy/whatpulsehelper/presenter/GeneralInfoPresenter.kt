package com.vchornenkyy.whatpulsehelper.presenter

import com.vchornenkyy.whatpulsehelper.api.Cache
import com.vchornenkyy.whatpulsehelper.api.InMemoryCache
import com.vchornenkyy.whatpulsehelper.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.helper.ModelConverter
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
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { userResponse ->
                    val user = ModelConverter().convert(userResponse)
                    view?.bindUser(user)
                    cache.saveUser(userResponse, user.generatedTime)
                }
    }

    fun detach() {
        userSubscription?.unsubscribe()
        userSubscription = null
    }
}
