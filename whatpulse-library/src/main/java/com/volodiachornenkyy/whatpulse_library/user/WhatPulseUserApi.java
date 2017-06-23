package com.volodiachornenkyy.whatpulse_library.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volodiachornenkyy.whatpulse_library.error.WhatPulseError;
import com.volodiachornenkyy.whatpulse_library.error.WhatPulseException;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class WhatPulseUserApi {
    private WhatPulseUserService userService;

    public WhatPulseUserApi(Retrofit retrofit) {
        userService = retrofit.create(WhatPulseUserService.class);
    }

    public Single<WhatPulseUser> getUser(String userId) {
        return userService.getUser(userId).map(new Function<ResponseBody, WhatPulseUser>() {
            @Override
            public WhatPulseUser apply(ResponseBody responseBody) throws Exception {
                String json = responseBody.string();
                ObjectMapper objectMapper = new ObjectMapper();

                WhatPulseError whatPulseError = objectMapper.readValue(json, WhatPulseError.class);
                if (whatPulseError.getError() != null) {
                    throw new WhatPulseException(whatPulseError.getError());
                }

                return objectMapper.readValue(json, WhatPulseUser.class);
            }
        });
    }
}
