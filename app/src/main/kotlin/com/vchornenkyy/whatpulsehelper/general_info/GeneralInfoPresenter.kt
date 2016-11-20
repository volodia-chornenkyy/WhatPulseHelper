package com.vchornenkyy.whatpulsehelper.general_info

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.api.Cache
import com.vchornenkyy.whatpulsehelper.common.api.InMemoryCache
import com.vchornenkyy.whatpulsehelper.common.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.helper.ModelConverter
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.net.UnknownHostException

class GeneralInfoPresenter constructor(val appProperties: AppProperties) {


    var view: GeneralInfoView? = null
    var userSubscription: Subscription? = null

    fun loadUser() {
        val userApi = WhatPulseRestApi().userApi
        val cache: Cache = InMemoryCache.instance
        val username = appProperties.getUsername()
        userSubscription = cache.getUser()
                .switchIfEmpty(userApi.getUser(username))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { userResponse ->
                            val user = ModelConverter().convert(userResponse)
                            view?.bindUser(user)
                            cache.saveUser(userResponse)
                        },
                        { error ->

                            if (error is UnknownHostException) {
                                view?.displayMessage("Please check internet connection")
                            }

                            Log.e(GeneralInfoPresenter::class.java.name, error.message, error)
                            // TODO display error message to UI
                        }
                )
    }

    fun detach() {
        userSubscription?.unsubscribe()
        userSubscription = null
    }
}
