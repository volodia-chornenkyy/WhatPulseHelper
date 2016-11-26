package com.vchornenkyy.whatpulsehelper.common.usecase

import com.vchornenkyy.whatpulsehelper.common.api.Cache
import com.vchornenkyy.whatpulsehelper.common.api.InMemoryCache
import com.vchornenkyy.whatpulsehelper.common.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.common.api.model.UserResponse
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.helper.ModelConverter
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

abstract class BaseUserUseCase(appProperties: AppProperties) {

    protected val properties = appProperties;
    protected val userApi = WhatPulseRestApi().userApi
    protected val cache: Cache = InMemoryCache.instance
    protected val converter = ModelConverter()

    protected fun getUser(userId: String): Observable<UserResponse> {
        return cache.getUser()
                .switchIfEmpty(userApi.getUser(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}