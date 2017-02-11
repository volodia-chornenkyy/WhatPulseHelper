package com.vchornenkyy.whatpulsehelper.domain.cache

import io.paperdb.Paper
import rx.Observable
import java.util.*

abstract class BasePaperCache<T> : BaseCache<T>() {

    protected abstract fun getDataKey(): String

    protected open fun getDataTimestampKey(): String {
        return getDataKey() + "Timestamp"
    }

    override fun save(data: T) {
        if (!isCacheValid()) {
            Paper.book().write(getDataKey(), data)
            Paper.book().write(getDataTimestampKey(), getCurrentTimestamp())
        }
    }

    override fun get(): Observable<T> {
        return Observable.defer {
            val data = Paper.book().read<T>(getDataKey())
            if (data == null || !isCacheValid()) {
                return@defer Observable.empty<T>()
            } else {
                return@defer Observable.just<T>(data)
            }
        }
    }

    override fun clear() {
        Paper.book().destroy()
    }

    override fun getTimestamp(): Long {
        return Paper.book().read(getDataTimestampKey(), 0L)
    }

    override fun getCurrentTimestamp(): Long {
        return Date().time
    }
}