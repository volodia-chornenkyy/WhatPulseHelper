package com.vchornenkyy.whatpulsehelper.dto

import java.util.*

data class User(
        val generatedTime: Long,
        val userId: Long,
        val accountName: String,
        val country: String?,
        val countryCode: String?,
        val dateJoined: String,
        val homePage: String?,
        val lastPulse: String,
        val pulsesAmount: String,
        val keysPressed: String,
        val clicksMade: String,
        val download: String,
        val upload: String,
        val uptime: String,
        val averageKeysPerPulse: String,
        val averageClicksPerPulse: String,
        val averageKeysPerSecond: String,
        val averageClicksPerSecond: String,
        val ranks: Ranks,
        val computers: HashMap<String, Computer>?
)