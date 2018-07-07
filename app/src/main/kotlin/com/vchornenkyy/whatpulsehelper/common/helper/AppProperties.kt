package com.vchornenkyy.whatpulsehelper.common.helper

import java.util.*

interface AppProperties {

    fun saveUsername(username: String)

    fun getUsername(): String

    fun getAppLocale(): Locale

    fun getLastUserLoadTimestamp(): Long

    fun setLastUserLoadTimestamp()

    fun clearLastUserLoadTimestamp()

    fun isUserLoadingAvailable(time: Long): Boolean
}