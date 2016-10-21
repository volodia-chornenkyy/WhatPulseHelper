package com.vchornenkyy.whatpulsehelper.api

import com.vchornenkyy.whatpulsehelper.api.model.UserResponse
import rx.Observable

interface Cache {
    fun saveUser(userResponse: UserResponse)

    fun getUser(): Observable<UserResponse>
}