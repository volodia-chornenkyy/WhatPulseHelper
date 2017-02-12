package com.vchornenkyy.whatpulsehelper.domain.dto

class Ranks {
    var keys: String? = null
    var clicks: String? = null
    var download: String? = null
    var upload: String? = null
    var uptime: String? = null

    constructor()

    constructor(keys: String?, clicks: String?, download: String?, upload: String?, uptime: String?) {
        this.keys = keys
        this.clicks = clicks
        this.download = download
        this.upload = upload
        this.uptime = uptime
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Ranks

        if (keys != other.keys) return false
        if (clicks != other.clicks) return false
        if (download != other.download) return false
        if (upload != other.upload) return false
        if (uptime != other.uptime) return false

        return true
    }
}