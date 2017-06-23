package com.volodiachornenkyy.whatpulse_library.pulses;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface WhatPulsePulsesService {

    @GET("pulses.php?format=json")
    Single<ResponseBody> getUserPulses(@Query("user") String userId, @Query("start") Long start, @Query("end") Long end);

    @GET("pulses.php?format=json")
    Single<ResponseBody> getTeamPulses(@Query("team") String teamId, @Query("start") Long start, @Query("end") Long end);
}