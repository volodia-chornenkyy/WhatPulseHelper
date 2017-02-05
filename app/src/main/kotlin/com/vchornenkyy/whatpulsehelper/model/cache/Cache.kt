package com.vchornenkyy.whatpulsehelper.model.cache

import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import rx.Observable

interface Cache {
    fun saveUser(userResponse: UserResponse)

    fun getUser(): Observable<UserResponse>

    fun clear()
}