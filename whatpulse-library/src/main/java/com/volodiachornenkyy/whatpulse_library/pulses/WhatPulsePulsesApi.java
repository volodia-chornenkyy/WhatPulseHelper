package com.volodiachornenkyy.whatpulse_library.pulses;

import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseBaseApi;

import java.io.IOException;
import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class WhatPulsePulsesApi extends WhatPulseBaseApi {

    private WhatPulsePulsesService pulsesService;

    private Function<ResponseBody, WhatPulsePulses> mapper;

    public WhatPulsePulsesApi(Retrofit retrofit) {
        pulsesService = retrofit.create(WhatPulsePulsesService.class);
        mapper = getMapperFunction(WhatPulsePulses.class);
    }

    @Override
    protected <T> T convert(Class<T> tClass, String json) throws IOException {
        WhatPulsePulsesMap pulses = objectMapper.readValue(json, WhatPulsePulsesMap.class);
        return (T) new WhatPulsePulses(pulses.values());
    }

    public Single<WhatPulsePulses> getUserPulses(String userId, Long start, Long end) {
        return pulsesService.getUserPulses(userId, start, end).map(mapper);
    }

    public Single<WhatPulsePulses> getUserPulses(String userId) {
        return getUserPulses(userId, null, null);
    }

    public Single<WhatPulsePulses> getTeamPulses(String teamId, Long start, Long end) {
        return pulsesService.getTeamPulses(teamId, start, end).map(mapper);
    }

    public Single<WhatPulsePulses> getTeamPulses(String teamId) {
        return getTeamPulses(teamId, null, null);
    }

    private class WhatPulsePulsesMap extends HashMap<String, WhatPulsePulse> {
    }
}