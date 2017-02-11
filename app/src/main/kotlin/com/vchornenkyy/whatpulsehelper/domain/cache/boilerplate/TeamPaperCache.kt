package com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate

import com.vchornenkyy.whatpulsehelper.domain.cache.BasePaperCache
import com.vchornenkyy.whatpulsehelper.domain.dto.Team

class TeamPaperCache : BasePaperCache<Team>() {

    override fun getDataKey(): String {
        return TeamPaperCache::class.java.simpleName
    }
}