package com.vchornenkyy.whatpulsehelper.model.api

import com.vchornenkyy.whatpulsehelper.model.api.model.UserResponse
import rx.Observable

interface Cache {
    fun saveUser(userResponse: UserResponse)

    fun getUser(): Observable<UserResponse>

    fun clear()
}