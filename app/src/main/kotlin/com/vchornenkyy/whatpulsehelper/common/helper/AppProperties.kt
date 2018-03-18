package com.vchornenkyy.whatpulsehelper.common.helper

import java.util.*

interface AppProperties {

    fun saveUsername(username: String)

    fun getUsername(): String

    fun getAppLocale(): Locale
}