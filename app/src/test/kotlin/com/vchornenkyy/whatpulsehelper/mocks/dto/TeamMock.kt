package com.vchornenkyy.whatpulsehelper.mocks.pojo

import com.vchornenkyy.whatpulsehelper.domain.dto.Team

class TeamMock {

    companion object {
        fun get(): Team {
            return Team(ranks = RankMock.get())
        }
    }
}