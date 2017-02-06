package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.UserResponsePaperCache
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.model.api.UserService
import com.vchornenkyy.whatpulsehelper.model.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

abstract class BaseUserWhatPulseUseCase(protected val properties: AppProperties,
                                        protected val userApi: UserService = WhatPulseRestApi().userApi,
                                        val cacheResponse: BaseCache<UserResponse> = UserResponsePaperCache(),
                                        protected val converter: ModelConverter = ModelConverter(),
                                        val subscribeOn: Scheduler = Schedulers.io(),
                                        val observeOn: Scheduler = AndroidSchedulers.mainThread()) {


    protected fun getBaseWhatPulseObservable(userId: String): Observable<UserResponse> {
        val apiObservable = userApi.getUser(userId)
                .map {
                    userResponse ->
                    cacheResponse.save(userResponse)
                    return@map userResponse
                }
        return cacheResponse.get()
                .switchIfEmpty(apiObservable)
    }
}