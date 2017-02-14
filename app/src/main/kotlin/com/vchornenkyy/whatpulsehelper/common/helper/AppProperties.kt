package com.vchornenkyy.whatpulsehelper.common.helper

interface AppProperties {

    fun saveUsername(username: String)

    fun getUsername(): String

    fun saveTeamName(teamName: String)

    fun getTeamName(): String
}