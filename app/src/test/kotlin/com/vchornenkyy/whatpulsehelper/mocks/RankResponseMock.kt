package com.vchornenkyy.whatpulsehelper.mocks

import com.vchornenkyy.whatpulsehelper.common.api.model.RanksResponse

class RankResponseMock {

    companion object {
        fun get(): RanksResponse {
            return RanksResponse(0, 0, 0, 0, 0)
        }
    }
}