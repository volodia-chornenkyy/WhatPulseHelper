package com.vchornenkyy.whatpulsehelper.dto

import java.util.*

class User() {
    var generatedTime: Long? = null
    var userId: Long? = null
    var accountName: String? = null
    var country: String? = null
    var countryCode: String? = null
    var dateJoined: String? = null
    var homePage: String? = null
    var lastPulse: String? = null
    var pulsesAmount: String? = null
    var keysPressed: String? = null
    var clicksMade: String? = null
    var download: String? = null
    var upload: String? = null
    var uptime: String? = null
    var averageKeysPerPulse: String? = null
    var averageClicksPerPulse: String? = null
    var averageKeysPerSecond: String? = null
    var averageClicksPerSecond: String? = null
    var ranks: Ranks? = null
    var computers: HashMap<String, Computer>? = null
}