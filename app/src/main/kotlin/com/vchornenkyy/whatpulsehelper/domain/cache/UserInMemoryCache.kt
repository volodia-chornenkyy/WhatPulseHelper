package com.vchornenkyy.whatpulsehelper.domain.cache

import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import rx.Observable
import java.util.*

class UserInMemoryCache private constructor() : BaseCache<UserResponse>() {

    private var userResponseTimestamp: Long = 0
    private var userResponse: UserResponse? = null

    private object Holder {
        val INSTANCE = UserInMemoryCache()
    }

    companion object {
        val instance: UserInMemoryCache by lazy { Holder.INSTANCE }
    }

    override fun save(data: UserResponse) {
        if (!isCacheValid()) {
            this.userResponseTimestamp = Date().time
            this.userResponse = userResponse
        }
    }

    override fun get(): Observable<UserResponse> {
        if (isCacheValid()) {
            return Observable.just(userResponse)
        }
        return Observable.empty()
    }

    override fun clear() {
        userResponse = null
        userResponseTimestamp = 0
    }

    override fun getTimestamp(): Long {
        return userResponseTimestamp
    }

    override fun getCurrentTimestamp(): Long {
        return Date().time
    }
}
