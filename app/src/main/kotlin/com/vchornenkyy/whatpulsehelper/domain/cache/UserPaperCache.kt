package com.vchornenkyy.whatpulsehelper.domain.cache

import com.vchornenkyy.whatpulsehelper.domain.dto.User

class UserPaperCache : BasePaperCache<User>() {

    override fun getDataTimestampKey(): String {
        return "userTimestamp"
    }

    override fun getDataKey(): String {
        return "user"
    }
}