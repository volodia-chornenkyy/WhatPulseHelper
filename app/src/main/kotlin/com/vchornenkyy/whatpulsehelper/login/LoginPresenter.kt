package com.vchornenkyy.whatpulsehelper.login

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.api.Cache
import com.vchornenkyy.whatpulsehelper.common.api.InMemoryCache
import com.vchornenkyy.whatpulsehelper.common.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.general_info.GeneralInfoPresenter
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginPresenter constructor(val appProperties: AppProperties,
                                 val eventTracker: EventTracker) {

    private var view: LoginView? = null
    private var userSubscription: Subscription? = null

    fun login(username: String) {
        if (username.length == 0) {
            view?.displayMessage("Please enter username")
            return
        }

        view?.showProgress(true)

        val userApi = WhatPulseRestApi().userApi
        val cache: Cache = InMemoryCache.instance
        userSubscription = userApi.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { userResponse ->
                            cache.saveUser(userResponse)

                            appProperties.saveUsername(username)

                            eventTracker.login()

                            view?.openMainScreen()
                        },
                        { error ->
                            view?.showProgress(false)

                            Log.e(GeneralInfoPresenter::class.java.name, error.message, error);
                            // TODO display error message to UI
                        }
                )
    }

    fun attach(view: LoginView) {
        this.view = view
    }

    fun detach() {
        view = null

        userSubscription?.unsubscribe()
        userSubscription = null
    }

}