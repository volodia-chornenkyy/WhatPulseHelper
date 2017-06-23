package com.volodiachornenkyy.whatpulse_library.pulses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volodiachornenkyy.whatpulse_library.error.WhatPulseError;
import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseException;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class WhatPulsePulsesApi {

    private WhatPulsePulsesService pulsesService;

    private Function<ResponseBody, List<WhatPulsePulse>> mapper;

    public WhatPulsePulsesApi(Retrofit retrofit) {
        pulsesService = retrofit.create(WhatPulsePulsesService.class);

        mapper = new Function<ResponseBody, List<WhatPulsePulse>>() {
            @Override
            public List<WhatPulsePulse> apply(ResponseBody responseBody) throws Exception {
                String json = responseBody.string();
                ObjectMapper objectMapper = new ObjectMapper();

                WhatPulseError whatPulseError = objectMapper.readValue(json, WhatPulseError.class);
                if (whatPulseError.getError() != null) {
                    throw new WhatPulseException(whatPulseError.getError());
                }

                WhatPulsePulses pulses = objectMapper.readValue(json, WhatPulsePulses.class);
                List<WhatPulsePulse> data = new ArrayList<>(pulses.size());
                data.addAll(pulses.values());
                return data;
            }
        };
    }

    public Single<List<WhatPulsePulse>> getUserPulses(String userId, Long start, Long end) {
        return pulsesService.getUserPulses(userId, start, end).map(mapper);
    }

    public Single<List<WhatPulsePulse>> getUserPulses(String userId) {
        return getUserPulses(userId, null, null);
    }

    public Single<List<WhatPulsePulse>> getTeamPulses(String teamId, Long start, Long end) {

        return pulsesService.getTeamPulses(teamId, start, end).map(mapper);
    }

    public Single<List<WhatPulsePulse>> getTeamPulses(String teamId) {
        return getTeamPulses(teamId, null, null);
    }
}
