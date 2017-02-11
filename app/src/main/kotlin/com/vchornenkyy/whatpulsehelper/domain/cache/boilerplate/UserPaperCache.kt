package com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate

import com.vchornenkyy.whatpulsehelper.domain.cache.BasePaperCache
import com.vchornenkyy.whatpulsehelper.domain.dto.User

class UserPaperCache : BasePaperCache<User>() {

    override fun getDataKey(): String {
        return UserPaperCache::class.java.simpleName
    }
}