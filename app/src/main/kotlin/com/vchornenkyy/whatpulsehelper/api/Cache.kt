package com.vchornenkyy.whatpulsehelper.api

import com.vchornenkyy.whatpulsehelper.api.model.UserResponse
import rx.Observable

interface Cache {
    fun saveUser(userResponse: UserResponse, timestamp: Long?)

    fun getUser(): Observable<UserResponse>
}