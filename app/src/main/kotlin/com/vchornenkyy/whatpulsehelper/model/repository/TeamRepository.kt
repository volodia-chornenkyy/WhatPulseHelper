package com.vchornenkyy.whatpulsehelper.model.repository

import com.vchornenkyy.whatpulsehelper.model.api.TeamService
import com.vchornenkyy.whatpulsehelper.model.api.WhatPulseRestApi
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.SubTeamsResponse
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamMembersResponse
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamResponse
import rx.Observable

class TeamRepository(val teamApi: TeamService = WhatPulseRestApi().getTeamApi()) : BaseRepository, TeamService {
    override fun getTeam(teamName: String): Observable<TeamResponse> {
        return teamApi.getTeam(teamName)
    }

    override fun getSubTeams(teamName: String): Observable<SubTeamsResponse> {
        return teamApi.getSubTeams(teamName)
    }

    override fun getMembers(teamName: String): Observable<TeamMembersResponse> {
        return teamApi.getMembers(teamName)
    }
}