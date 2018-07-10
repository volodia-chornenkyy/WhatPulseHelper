package com.vchornenkyy.whatpulsehelper.common.api

import android.content.Context
import android.content.SharedPreferences
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.vchornenkyy.whatpulsehelper.common.api.model.UserResponse
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import io.reactivex.Observable
import java.util.*

// TODO optimize loading, to much work for GC after this))
class SharedPrefCache(context: Context, private val properties: AppProperties) : Cache {

    private val pref: SharedPreferences = context.getSharedPreferences("cache", Context.MODE_PRIVATE)
    private val USER_KEY = "user"

    override fun saveUser(userResponse: UserResponse) {
        if (properties.isUserLoadingAvailable(Date().time)) {
            properties.setLastUserLoadTimestamp()
            val writeValueAsString = jacksonObjectMapper().writeValueAsString(userResponse)
            pref.edit().putString(USER_KEY, writeValueAsString).apply()
        }
    }

    override fun getUser(): Observable<UserResponse> {
        if (!properties.isUserLoadingAvailable(Date().time)) {

            return Observable.create { emitter ->
                val storedCacheJson = pref.getString(USER_KEY, "")
                val userResponse = jacksonObjectMapper().readValue<UserResponse>(storedCacheJson, UserResponse::class.java)
                emitter.onNext(userResponse)
                emitter.onComplete()
            }
        }
        return Observable.empty()
    }

    override fun clear() {
        pref.edit().remove(USER_KEY).apply()
        properties.clearLastUserLoadTimestamp()
    }
}
