package com.volodiachornenkyy.whatpulse_library.pulses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatPulsePulses extends HashMap<String, WhatPulsePulse> {

}
