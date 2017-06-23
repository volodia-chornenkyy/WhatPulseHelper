package com.volodiachornenkyy.whatpulse_library.teams;

import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseBaseApi;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class WhatPulseTeamApi extends WhatPulseBaseApi {
    private WhatPulseTeamService teamService;

    public WhatPulseTeamApi(Retrofit retrofit) {
        teamService = retrofit.create(WhatPulseTeamService.class);
    }

    public Single<WhatPulseTeam> getTeam(String teamId) {
        return teamService.getTeam(teamId).map(getMapperFunction(WhatPulseTeam.class));
    }
}
