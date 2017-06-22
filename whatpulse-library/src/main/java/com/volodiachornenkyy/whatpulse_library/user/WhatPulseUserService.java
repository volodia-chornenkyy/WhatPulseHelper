package com.volodiachornenkyy.whatpulse_library.user;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

interface WhatPulseUserService {

    @GET("user.php?format=json")
    Single<WhatPulseUser> getUser(@Query("user") String userId);
}