package com.volodiachornenkyy.whatpulse_library.user;

import com.volodiachornenkyy.whatpulse_library.shared.WhatPulseError;

public enum WhatPulseUserError {
    NO_USER_GIVEN("No User given!"),
    UNKNOWN_USER("Unknown UserID given");

    private String message;

    WhatPulseUserError(String errorMessage) {
        this.message = errorMessage;
    }

    @Override
    public String toString() {
        return message;
    }

    public static WhatPulseUserError define(WhatPulseError error) {
        String backendError = error.getError();
        WhatPulseUserError[] values = values();
        for(WhatPulseUserError whatPulseError : values) {
            if (whatPulseError.message.equals(backendError)) {
                return whatPulseError;
            }
        }
        return null;
    }
}
