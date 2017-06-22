package com.volodiachornenkyy.whatpulse_library;

import com.volodiachornenkyy.whatpulse_library.user.WhatPulseUserService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class WhatPulseClient {

    private Retrofit retrofit;

    private WhatPulseUserService userService;

    public WhatPulseClient() {
        this(new OkHttpClient());
    }

    public WhatPulseClient(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.whatpulse.org/")
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public WhatPulseUserService getUserService() {
        if (userService == null) {
            userService = retrofit.create(WhatPulseUserService.class);
        }
        return userService;
    }
}
