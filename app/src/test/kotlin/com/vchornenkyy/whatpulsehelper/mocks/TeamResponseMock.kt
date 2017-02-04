package com.vchornenkyy.whatpulsehelper.mocks

import com.vchornenkyy.whatpulsehelper.common.api.model.TeamResponse

class TeamResponseMock {

    companion object {

        fun get(): TeamResponse {
            return TeamResponse()
        }
    }
}