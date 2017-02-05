package com.vchornenkyy.whatpulsehelper.domain

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.model.api.UserService
import com.vchornenkyy.whatpulsehelper.model.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.model.cache.Cache
import com.vchornenkyy.whatpulsehelper.model.cache.InMemoryCache
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginUseCase(appProperties: AppProperties,
                   userApi: UserService = WhatPulseRestApi().userApi,
                   cache: Cache = InMemoryCache.instance,
                   converter: ModelConverter = ModelConverter(),
                   subscribeOn: Scheduler = Schedulers.io(),
                   observeOn: Scheduler = AndroidSchedulers.mainThread()) : BaseUserUseCase(appProperties, userApi, cache, converter, subscribeOn, observeOn) {

    fun execute(userId: String): Observable<Boolean> {
        return getUser(userId)
                .map { user ->
                    properties.saveUsername(user.accountName!!)
                    return@map user != null
                }
    }
}