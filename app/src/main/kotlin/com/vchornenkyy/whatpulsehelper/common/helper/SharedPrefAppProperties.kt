package com.vchornenkyy.whatpulsehelper.common.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPrefAppProperties(context: Context) : AppProperties {

    private val pref: SharedPreferences = context.getSharedPreferences("app_properties", Context.MODE_PRIVATE)

    private val KEY_USERNAME = "username"

    private val KEY_TEAM_NAME = "team_name"

    override fun saveUsername(username: String) {
        pref.edit().putString(KEY_USERNAME, username).apply()
    }

    override fun getUsername(): String {
        return pref.getString(KEY_USERNAME, "")
    }

    override fun saveTeamName(teamName: String) {
        pref.edit().putString(KEY_TEAM_NAME, teamName).apply()
    }

    override fun getTeamName(): String {
        return pref.getString(KEY_TEAM_NAME, "")
    }
}