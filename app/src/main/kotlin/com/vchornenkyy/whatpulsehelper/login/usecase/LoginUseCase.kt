package com.vchornenkyy.whatpulsehelper.login.usecase

import com.vchornenkyy.whatpulsehelper.common.api.Cache
import com.vchornenkyy.whatpulsehelper.common.api.InMemoryCache
import com.vchornenkyy.whatpulsehelper.common.api.UserService
import com.vchornenkyy.whatpulsehelper.common.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.usecase.BaseUserUseCase
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginUseCase(appProperties: AppProperties,
                   userApi: UserService = WhatPulseRestApi().userApi,
                   cache: Cache = InMemoryCache.instance,
                   subscribeOn: Scheduler = Schedulers.io(),
                   observeOn: Scheduler = AndroidSchedulers.mainThread()) : BaseUserUseCase(appProperties, userApi, cache, subscribeOn, observeOn) {

    fun execute(userId: String): Observable<User> {
        return getUser(userId)
    }
}