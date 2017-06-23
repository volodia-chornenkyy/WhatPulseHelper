package com.volodiachornenkyy.whatpulse_library.teams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseError;
import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseException;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class WhatPulseTeamApi {
    private WhatPulseTeamService teamService;

    public WhatPulseTeamApi(Retrofit retrofit) {
        teamService = retrofit.create(WhatPulseTeamService.class);
    }

    public Single<WhatPulseTeam> getTeam(String teamId) {
        return teamService.getTeam(teamId).map(new Function<ResponseBody, WhatPulseTeam>() {
            @Override
            public WhatPulseTeam apply(ResponseBody responseBody) throws Exception {
                String json = responseBody.string();
                ObjectMapper objectMapper = new ObjectMapper();

                WhatPulseError whatPulseError = objectMapper.readValue(json, WhatPulseError.class);
                if (whatPulseError.getError() != null) {
                    throw new WhatPulseException(whatPulseError.getError());
                }

                return objectMapper.readValue(json, WhatPulseTeam.class);
            }
        });
    }
}
