package com.vchornenkyy.whatpulsehelper.mocks.pojo

import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamResponse

class TeamResponseMock {

    companion object {

        fun get(): TeamResponse {
            return TeamResponse(ranks = RankResponseMock.get())
        }
    }
}