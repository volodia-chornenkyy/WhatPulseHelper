package com.vchornenkyy.whatpulsehelper.domain.cache

import rx.Observable

abstract class BaseCache<T> {

    protected val timeout = 3600000

    abstract fun saveUser(data: T)

    abstract fun getUser(): Observable<T>

    abstract fun clear()

    abstract fun getTimestamp(): Long

    abstract fun getCurrentTimestamp(): Long

    protected fun isCacheValid(): Boolean {
        val currentTimestamp = getCurrentTimestamp()
        val dataCachingTimestamp = getTimestamp()
        val timePassed = currentTimestamp - dataCachingTimestamp
        return timePassed < timeout
    }
}