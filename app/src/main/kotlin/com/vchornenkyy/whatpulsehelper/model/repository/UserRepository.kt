package com.vchornenkyy.whatpulsehelper.model.repository

import com.vchornenkyy.whatpulsehelper.model.api.UserService
import com.vchornenkyy.whatpulsehelper.model.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import rx.Observable

class UserRepository(val userApi: UserService = WhatPulseRestApi().userApi) : BaseRepository {

    fun getUser(userName: String): Observable<UserResponse> {
        return userApi.getUser(userName)
    }
}