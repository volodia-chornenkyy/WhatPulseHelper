package com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate

import com.vchornenkyy.whatpulsehelper.domain.cache.BasePaperCache
import com.vchornenkyy.whatpulsehelper.domain.dto.Computer

class ComputersPaperCache : BasePaperCache<List<Computer>>() {

    override fun getDataKey(): String {
        return ComputersPaperCache::class.java.simpleName
    }
}