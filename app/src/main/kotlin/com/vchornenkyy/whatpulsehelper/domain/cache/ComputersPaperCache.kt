package com.vchornenkyy.whatpulsehelper.domain.cache

import com.vchornenkyy.whatpulsehelper.domain.dto.Computer

class ComputersPaperCache : BasePaperCache<List<Computer>>() {

    override fun getDataTimestampKey(): String {
        return "computersTimestamp"
    }

    override fun getDataKey(): String {
        return "computers"
    }
}