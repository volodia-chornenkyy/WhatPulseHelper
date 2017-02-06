package com.vchornenkyy.whatpulsehelper.domain.usecases.auth

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.UserResponsePaperCache
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.domain.usecases.BaseUserWhatPulseUseCase
import com.vchornenkyy.whatpulsehelper.model.api.UserService
import com.vchornenkyy.whatpulsehelper.model.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginUseCase(appProperties: AppProperties,
                   userApi: UserService = WhatPulseRestApi().userApi,
                   cache: BaseCache<UserResponse> = UserResponsePaperCache(),
                   converter: ModelConverter = ModelConverter(),
                   subscribeOn: Scheduler = Schedulers.io(),
                   observeOn: Scheduler = AndroidSchedulers.mainThread()) : BaseUserWhatPulseUseCase(appProperties, userApi, cache, converter, subscribeOn, observeOn) {

    fun execute(userId: String): Observable<Boolean> {
        return getBaseWhatPulseObservable(userId)
                .map { user ->
                    properties.saveUsername(user.accountName)
                    return@map user != null
                }
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }
}