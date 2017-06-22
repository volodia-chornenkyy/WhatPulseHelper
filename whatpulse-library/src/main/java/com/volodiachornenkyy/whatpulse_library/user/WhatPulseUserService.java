package com.volodiachornenkyy.whatpulse_library.user;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WhatPulseUserService {

    @GET("user.php?format=json")
    Call<WhatPulseUser> getUser(@Query("user") String userId);
}