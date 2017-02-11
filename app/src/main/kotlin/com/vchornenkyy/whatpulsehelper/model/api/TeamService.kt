package com.vchornenkyy.whatpulsehelper.model.api

import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.SubTeamsResponse
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamMembersResponse
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface TeamService {

    @GET("team.php?format=json")
    fun getTeam(@Query("team") teamName: String): Observable<TeamResponse>

    @GET("team.php?format=json")
    fun getSubTeams(@Query("team") teamName: String): Observable<SubTeamsResponse>

    @GET("team.php?format=json&members=yes")
    fun getMembers(@Query("team") teamName: String): Observable<TeamMembersResponse>
}