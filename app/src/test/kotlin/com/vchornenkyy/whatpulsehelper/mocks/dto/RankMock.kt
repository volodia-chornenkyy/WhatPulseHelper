package com.vchornenkyy.whatpulsehelper.mocks.pojo

import com.vchornenkyy.whatpulsehelper.domain.dto.Ranks

class RankMock {

    companion object {
        fun get(): Ranks {
            return Ranks("1", "2", "3", "4", "5")
        }
    }
}