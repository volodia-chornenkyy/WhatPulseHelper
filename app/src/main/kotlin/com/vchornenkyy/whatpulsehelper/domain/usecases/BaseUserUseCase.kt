package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.dto.User
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.model.api.UserService
import com.vchornenkyy.whatpulsehelper.model.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.model.cache.Cache
import com.vchornenkyy.whatpulsehelper.model.cache.InMemoryCache
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

abstract class BaseUserUseCase(protected val properties: AppProperties,
                               protected val userApi: UserService = WhatPulseRestApi().userApi,
                               protected val cache: Cache = InMemoryCache.instance,
                               protected val converter: ModelConverter = ModelConverter(),
                               val subscribeOn: Scheduler = Schedulers.io(),
                               val observeOn: Scheduler = AndroidSchedulers.mainThread()) {

    protected fun getUser(userId: String): Observable<User> {
        return cache.getUser()
                .switchIfEmpty(userApi.getUser(userId))
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .map { userResponse ->
                    cache.saveUser(userResponse)
                    return@map converter.convert(userResponse)
                }
    }
}