package com.vchornenkyy.whatpulsehelper.mocks

import com.vchornenkyy.whatpulsehelper.model.api.model.TeamResponse

class TeamResponseMock {

    companion object {

        fun get(): TeamResponse {
            return TeamResponse()
        }
    }
}