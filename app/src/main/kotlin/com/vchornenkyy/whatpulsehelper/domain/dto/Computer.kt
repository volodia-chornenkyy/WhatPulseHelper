package com.vchornenkyy.whatpulsehelper.domain.dto

class Computer {
    var name: String = ""
    var keys: String? = null
    var clicks: String? = null
    var download: String? = null
    var upload: String? = null
    var uptime: String? = null
    var pulses: String? = null
    var lastPulse: String? = null

    constructor()

    constructor(name: String, keys: String?, clicks: String?, download: String?, upload: String?, uptime: String?, pulses: String?, lastPulse: String?) {
        this.name = name
        this.keys = keys
        this.clicks = clicks
        this.download = download
        this.upload = upload
        this.uptime = uptime
        this.pulses = pulses
        this.lastPulse = lastPulse
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Computer

        if (name != other.name) return false
        if (keys != other.keys) return false
        if (clicks != other.clicks) return false
        if (download != other.download) return false
        if (upload != other.upload) return false
        if (uptime != other.uptime) return false
        if (pulses != other.pulses) return false
        if (lastPulse != other.lastPulse) return false

        return true
    }
}