package com.volodiachornenkyy.whatpulse_library.user;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import retrofit2.Retrofit;

public class WhatPulseUserApi {
    private WhatPulseUserService userService;

    public WhatPulseUserApi(Retrofit retrofit) {
        userService = retrofit.create(WhatPulseUserService.class);
    }

    public Single<WhatPulseUser> getUser(String userId) {
        return userService.getUser(userId).doOnSuccess(new Consumer<WhatPulseUser>() {
            @Override
            public void accept(WhatPulseUser whatPulseUser) throws Exception {
                String error = whatPulseUser.getError();
                if (error != null) {
                    WhatPulseUserError whatPulseUserError = WhatPulseUserError.define(whatPulseUser);
                    boolean knownError = whatPulseUserError != null;
                    throw new RuntimeException(knownError ? whatPulseUserError.toString() : error);
                }
            }
        });
    }
}
