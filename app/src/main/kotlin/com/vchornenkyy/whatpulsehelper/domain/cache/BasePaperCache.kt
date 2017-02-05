package com.vchornenkyy.whatpulsehelper.domain.cache

import io.paperdb.Paper
import rx.Observable
import java.util.*

abstract class BasePaperCache<T> : BaseCache<T>() {

    protected abstract fun getDataKey(): String

    protected abstract fun getDataTimestampKey(): String

    override fun save(data: T) {
        if (!isCacheValid()) {
            Paper.book().write(getDataKey(), data)
            Paper.book().write(getDataTimestampKey(), getCurrentTimestamp())
        }
    }

    override fun get(): Observable<T> {
        return Observable.defer {
            val userResponse = Paper.book().read<T>(getDataKey())
            if (userResponse == null || !isCacheValid()) {
                return@defer Observable.empty<T>()
            } else {
                return@defer Observable.just<T>(userResponse)
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