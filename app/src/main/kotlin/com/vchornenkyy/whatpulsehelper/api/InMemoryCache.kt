package com.vchornenkyy.whatpulsehelper.api

import com.vchornenkyy.whatpulsehelper.api.model.UserResponse
import rx.Observable

class InMemoryCache private constructor() : Cache {
    private val timeout = 3600000
    private var userResponseTimestamp: Long = 0;
    private var userResponse: UserResponse? = null;

    private object Holder {
        val INSTANCE = InMemoryCache()
    }

    companion object {
        val instance: InMemoryCache by lazy { Holder.INSTANCE }
    }

    override fun saveUser(userResponse: UserResponse, timestamp: Long?) {
        if (timestamp == null) {
            this.userResponseTimestamp = System.currentTimeMillis()
        } else {
            this.userResponseTimestamp = timestamp
        }
        this.userResponse = userResponse
    }

    override fun getUser(): Observable<UserResponse> {
        val timePassed = System.currentTimeMillis() - userResponseTimestamp
        if (timePassed > timeout) {
            userResponse = null
            return Observable.empty()
        }
        return Observable.just(userResponse)
    }
}