package com.volodiachornenkyy.whatpulse_library;

import com.volodiachornenkyy.whatpulse_library.pulses.WhatPulsePulsesApi;
import com.volodiachornenkyy.whatpulse_library.teams.WhatPulseTeamApi;
import com.volodiachornenkyy.whatpulse_library.user.WhatPulseUserApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class WhatPulseClient {

    private Retrofit retrofit;

    private WhatPulseUserApi userApi;
    private WhatPulsePulsesApi pulsesApi;
    private WhatPulseTeamApi teamApi;

    public WhatPulseClient() {
        this(new OkHttpClient());
    }

    public WhatPulseClient(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.whatpulse.org/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public WhatPulseUserApi getUserApi() {
        if (userApi == null) {
            userApi = new WhatPulseUserApi(retrofit);
        }
        return userApi;
    }

    public WhatPulsePulsesApi getPulsesApi() {
        if (pulsesApi == null) {
            pulsesApi = new WhatPulsePulsesApi(retrofit);
        }
        return pulsesApi;
    }

    public WhatPulseTeamApi getTeamApi() {
        if (teamApi == null) {
            teamApi = new WhatPulseTeamApi(retrofit);
        }
        return teamApi;
    }
}
