package com.vchornenkyy.whatpulsehelper.domain.cache

import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse

class UserPaperCache : BasePaperCache<UserResponse>() {

    override fun getDataTimestampKey(): String {
        return "userResponseTimestamp"
    }

    override fun getDataKey(): String {
        return "userResponse"
    }
}