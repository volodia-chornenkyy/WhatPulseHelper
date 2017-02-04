package com.vchornenkyy.whatpulsehelper.model.api

import com.vchornenkyy.whatpulsehelper.model.api.model.UserResponse
import rx.Observable
import java.util.*

class InMemoryCache private constructor() : Cache {
    private val timeout = 3600000
    private var userResponseTimestamp: Long = 0
    private var userResponse: UserResponse? = null

    private object Holder {
        val INSTANCE = InMemoryCache()
    }

    companion object {
        val instance: InMemoryCache by lazy { Holder.INSTANCE }
    }

    override fun saveUser(userResponse: UserResponse) {
        if (!isCacheValid(Date().time)) {
            this.userResponseTimestamp = Date().time
            this.userResponse = userResponse
        }
    }

    override fun getUser(): Observable<UserResponse> {
        if (isCacheValid(Date().time)) {
            return Observable.just(userResponse)
        }
        return Observable.empty()
    }

    override fun clear() {
        userResponse = null
        userResponseTimestamp = 0
    }

    private fun isCacheValid(currentTime: Long): Boolean {
        val timePassed = currentTime - userResponseTimestamp
        return timePassed < timeout
    }
}
