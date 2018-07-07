package com.vchornenkyy.whatpulsehelper.common.api

import com.vchornenkyy.whatpulsehelper.common.api.model.UserResponse
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import io.reactivex.Observable
import java.util.*

class InMemoryCache(private val properties: AppProperties) : Cache {


    private companion object Holder {
        var CACHE: UserResponse? = null
    }

    override fun saveUser(userResponse: UserResponse) {
        if (!properties.isUserLoadingAvailable(Date().time)) {
            properties.setLastUserLoadTimestamp()
            Holder.CACHE = userResponse
        }
    }

    override fun getUser(): Observable<UserResponse> {
        if (properties.isUserLoadingAvailable(Date().time) && Holder.CACHE != null) {
            return Observable.just(Holder.CACHE)
        }
        return Observable.empty()
    }

    override fun clear() {
        Holder.CACHE = null
        properties.clearLastUserLoadTimestamp()
    }
}
