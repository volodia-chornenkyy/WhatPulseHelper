package com.volodiachornenkyy.whatpulse_library.user;

import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseBaseApi;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class WhatPulseUserApi extends WhatPulseBaseApi {
    private WhatPulseUserService userService;

    public WhatPulseUserApi(Retrofit retrofit) {
        userService = retrofit.create(WhatPulseUserService.class);
    }

    public Single<WhatPulseUser> getUser(String userId) {
        return userService.getUser(userId).map(getMapperFunction(WhatPulseUser.class));
    }
}
