package com.volodiachornenkyy.whatpulse_library.shared;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WhatPulseComputer {

    @JsonProperty("UptimeLong")
    private String uptimeLong;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("ComputerID")
    private String computerId;

    @JsonProperty("UptimeSeconds")
    private String uptimeSeconds;

    @JsonProperty("LastPulseUnixTimestamp")
    private String lastPulseUnixTimestamp;

    @JsonProperty("Pulses")
    private String pulses;

    @JsonProperty("LastPulse")
    private String lastPulse;

    @JsonProperty("Upload")
    private String upload;

    @JsonProperty("Download")
    private String download;

    @JsonProperty("Keys")
    private String keys;

    @JsonProperty("UptimeShort")
    private String uptimeShort;

    @JsonProperty("Clicks")
    private String clicks;

    @Override
    public String toString() {
        return "WhatPulseComputer{" +
                "uptimeLong='" + uptimeLong + '\'' +
                ", name='" + name + '\'' +
                ", computerId='" + computerId + '\'' +
                ", uptimeSeconds='" + uptimeSeconds + '\'' +
                ", lastPulseUnixTimestamp='" + lastPulseUnixTimestamp + '\'' +
                ", pulses='" + pulses + '\'' +
                ", lastPulse='" + lastPulse + '\'' +
                ", upload='" + upload + '\'' +
                ", download='" + download + '\'' +
                ", keys='" + keys + '\'' +
                ", uptimeShort='" + uptimeShort + '\'' +
                ", clicks='" + clicks + '\'' +
                '}';
    }
}
