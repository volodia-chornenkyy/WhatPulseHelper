package com.vchornenkyy.whatpulsehelper.mocks

import com.vchornenkyy.whatpulsehelper.model.api.pojo.RanksResponse

class RankResponseMock {

    companion object {
        fun get(): RanksResponse {
            return RanksResponse(0, 0, 0, 0, 0)
        }
    }
}