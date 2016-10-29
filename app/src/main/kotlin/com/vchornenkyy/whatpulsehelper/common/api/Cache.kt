package com.vchornenkyy.whatpulsehelper.common.api

import com.vchornenkyy.whatpulsehelper.common.api.model.UserResponse
import rx.Observable

interface Cache {
    fun saveUser(userResponse: UserResponse)

    fun getUser(): Observable<UserResponse>

    fun clear()
}