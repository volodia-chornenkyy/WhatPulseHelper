package com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate

import com.vchornenkyy.whatpulsehelper.domain.cache.BasePaperCache
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamResponse

class TeamResponsePaperCache : BasePaperCache<TeamResponse>() {

    override fun getDataKey(): String {
        return TeamResponsePaperCache::class.java.simpleName
    }
}