package com.vchornenkyy.whatpulsehelper.common.api

import com.vchornenkyy.whatpulsehelper.common.api.model.UserResponse
import io.reactivex.Observable

interface Cache {
    fun saveUser(userResponse: UserResponse)

    fun getUser(): Observable<UserResponse>

    fun clear()
}