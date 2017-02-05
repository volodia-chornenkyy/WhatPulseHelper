package com.vchornenkyy.whatpulsehelper.domain.cache

import rx.Observable

abstract class BaseCache<T> {

    protected val timeout = 3600000

    abstract fun save(data: T)

    abstract fun get(): Observable<T>

    abstract fun clear()

    protected abstract fun getTimestamp(): Long

    protected abstract fun getCurrentTimestamp(): Long

    protected fun isCacheValid(): Boolean {
        val currentTimestamp = getCurrentTimestamp()
        val dataCachingTimestamp = getTimestamp()
        val timePassed = currentTimestamp - dataCachingTimestamp
        return timePassed < timeout
    }
}