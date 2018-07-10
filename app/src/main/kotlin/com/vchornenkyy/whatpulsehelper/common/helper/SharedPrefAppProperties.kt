@file:Suppress("PrivatePropertyName")

package com.vchornenkyy.whatpulsehelper.common.helper

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class SharedPrefAppProperties(context: Context) : AppProperties {

    private val pref: SharedPreferences = context.getSharedPreferences("app_properties", Context.MODE_PRIVATE)

    private val USER_LOADING_TIMEOUT = 3600000

    private val KEY_USERNAME = "username"
    private val KEY_USER_LOAD_TIMESTAMP = "user_load_timestamp"

    override fun saveUsername(username: String) {
        pref.edit().putString(KEY_USERNAME, username).apply()
    }

    override fun getUsername(): String {
        return pref.getString(KEY_USERNAME, "") // todo change it when login screen will be created
    }

    override fun getAppLocale(): Locale {
        return Locale.getDefault();
    }

    /**
     * return 0 if value was not set
     */
    override fun getLastUserLoadTimestamp(): Long {
        return pref.getLong(KEY_USER_LOAD_TIMESTAMP, 0)
    }

    /**
     * Set when the user was loaded based on the current date time stamp.
     */
    override fun setLastUserLoadTimestamp() {
        pref.edit().putLong(KEY_USER_LOAD_TIMESTAMP, Date().time).apply()
    }

    override fun clearLastUserLoadTimestamp() {
        pref.edit().remove(KEY_USER_LOAD_TIMESTAMP).apply()
    }

    override fun isUserLoadingAvailable(time: Long): Boolean {
        val lastUserLoadTimestamp = getLastUserLoadTimestamp()
        if (lastUserLoadTimestamp == 0L) return true
        val timePassed = time - lastUserLoadTimestamp
        return timePassed >= USER_LOADING_TIMEOUT
    }
}