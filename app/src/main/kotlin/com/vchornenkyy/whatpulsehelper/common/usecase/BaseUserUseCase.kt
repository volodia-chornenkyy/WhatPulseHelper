package com.vchornenkyy.whatpulsehelper.common.usecase

import android.content.Context
import com.vchornenkyy.whatpulsehelper.common.api.Cache
import com.vchornenkyy.whatpulsehelper.common.api.SharedPrefCache
import com.vchornenkyy.whatpulsehelper.common.api.UserService
import com.vchornenkyy.whatpulsehelper.common.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.helper.ModelConverter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class BaseUserUseCase(context: Context,
                               protected val properties: AppProperties,
                               private val userApi: UserService = WhatPulseRestApi().userApi) {

    protected val cache: Cache = SharedPrefCache(context, properties)
    protected val converter = ModelConverter(properties.getAppLocale())

    protected fun getUser(userId: String): Observable<User> {
        return cache.getUser()
                .switchIfEmpty(userApi.getUser(userId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { userResponse ->
                    cache.saveUser(userResponse)
                    return@map converter.convert(userResponse)
                }
    }
}