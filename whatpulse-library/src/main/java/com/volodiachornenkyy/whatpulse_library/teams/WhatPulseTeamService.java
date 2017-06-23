package com.volodiachornenkyy.whatpulse_library.teams;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface WhatPulseTeamService {

    @GET("team.php?format=json")
    Single<ResponseBody> getTeam(@Query("team") String teamId);
}