package com.volodiachornenkyy.whatpulse_library.user;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface WhatPulseUserService {

    @GET("user.php?format=json")
    Single<ResponseBody> getUser(@Query("user") String userId);
}