package com.vchornenkyy.whatpulsehelper.mocks

import com.vchornenkyy.whatpulsehelper.model.api.pojo.TeamResponse

class TeamResponseMock {

    companion object {

        fun get(): TeamResponse {
            return TeamResponse()
        }
    }
}