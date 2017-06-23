package com.volodiachornenkyy.whatpulse_library.error;

public enum WhatPulseErrors {
    // /user errors
    NO_USER_GIVEN("No User given!"),
    UNKNOWN_USER("Unknown UserID given"),

    // TODO /pulses errors
    NO_PULSES_FOUND("No pulses found!"),

    // TODO /team errors

    UNKNOWN("Unknown");

    private String message;

    WhatPulseErrors(String errorMessage) {
        this.message = errorMessage;
    }

    @Override
    public String toString() {
        return message;
    }

    public static WhatPulseErrors define(String error) {
        WhatPulseErrors[] values = values();
        for (WhatPulseErrors whatPulseError : values) {
            if (whatPulseError.message.equals(error)) {
                return whatPulseError;
            }
        }
        return UNKNOWN;
    }
}
