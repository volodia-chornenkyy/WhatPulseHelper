package com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate

import com.vchornenkyy.whatpulsehelper.domain.cache.BasePaperCache
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse

class UserResponsePaperCache : BasePaperCache<UserResponse>() {

    override fun getDataKey(): String {
        return UserResponsePaperCache::class.java.simpleName
    }
}