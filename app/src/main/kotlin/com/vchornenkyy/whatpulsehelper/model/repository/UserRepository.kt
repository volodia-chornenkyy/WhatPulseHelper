package com.vchornenkyy.whatpulsehelper.model.repository

import com.vchornenkyy.whatpulsehelper.model.api.UserService
import com.vchornenkyy.whatpulsehelper.model.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import rx.Observable

class UserRepository(val userApi: UserService = WhatPulseRestApi().getUserApi()) : BaseRepository, UserService {

    override fun getUser(username: String): Observable<UserResponse> {
        return userApi.getUser(username)
    }
}