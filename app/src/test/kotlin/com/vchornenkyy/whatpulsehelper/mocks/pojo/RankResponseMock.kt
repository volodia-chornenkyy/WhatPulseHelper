package com.vchornenkyy.whatpulsehelper.mocks.pojo

import com.vchornenkyy.whatpulsehelper.model.api.pojo.RanksResponse

class RankResponseMock {

    companion object {
        fun get(): RanksResponse {
            return RanksResponse(1, 2, 3, 4, 5)
        }
    }
}