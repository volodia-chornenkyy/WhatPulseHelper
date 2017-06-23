package com.volodiachornenkyy.whatpulse_library.shared;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.volodiachornenkyy.whatpulse_library.error.WhatPulseError;
import com.volodiachornenkyy.whatpulse_library.error.WhatPulseException;

import java.io.IOException;

import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

public abstract class WhatPulseBaseApi {

    protected ObjectMapper objectMapper = new ObjectMapper();

    protected <T> Function<ResponseBody, T> getMapperFunction(final Class<T> tClass) {
        return new Function<ResponseBody, T>() {
            @Override
            public T apply(ResponseBody responseBody) throws Exception {
                String json = responseBody.string();

                handleError(json);

                return convert(tClass, json);
            }
        };
    }

    protected <T> T convert(Class<T> tClass, String json) throws IOException {
        return objectMapper.readValue(json, tClass);
    }

    private void handleError(String json) throws java.io.IOException {
        WhatPulseError whatPulseError = objectMapper.readValue(json, WhatPulseError.class);
        if (whatPulseError.getError() != null) {
            throw new WhatPulseException(whatPulseError.getError());
        }
    }
}
