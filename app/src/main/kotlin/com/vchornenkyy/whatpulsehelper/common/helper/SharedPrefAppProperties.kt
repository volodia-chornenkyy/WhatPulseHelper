package com.vchornenkyy.whatpulsehelper.common.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPrefAppProperties : AppProperties {

    private val pref: SharedPreferences

    private val KEY_USERNAME = "username"

    constructor(context: Context) {
        pref = context.getSharedPreferences("app_properties", Context.MODE_PRIVATE)
    }

    override fun saveUsername(username: String) {
        pref.edit().putString(KEY_USERNAME, username).apply()
    }

    override fun getUsername(): String {
        return pref.getString(KEY_USERNAME, "") // todo change it when login screen will be created
    }
}